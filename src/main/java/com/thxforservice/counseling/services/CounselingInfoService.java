package com.thxforservice.counseling.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counseling.constants.Status;
import com.thxforservice.counseling.controllers.CounselingSearch;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.entities.QCounseling;
import com.thxforservice.counseling.exceptions.CounselingNotFoundException;
import com.thxforservice.counseling.repositories.CounselingRepository;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Pagination;
import com.thxforservice.global.exceptions.UnAuthorizedException;
import com.thxforservice.member.MemberUtil;
import com.thxforservice.member.entities.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CounselingInfoService {

        private final JPAQueryFactory queryFactory; //목록 조회 패치 조인을 위함
        private final CounselingRepository counselingRepository;
        private final HttpServletRequest request;
        private final MemberUtil memberUtil;

        /**
         * 예약 상세 정보 조회
         *
         * @param cSeq
         * @return
         */
        public Counseling get(Long cSeq) {
            Counseling counseling = counselingRepository.findById(cSeq).orElseThrow(CounselingNotFoundException::new);

            //추가 정보 처리
            addInfo(counseling);

            return counseling;
        }

        public Counseling get(Long seq, boolean isMine) {
            Counseling counseling = get(seq);

            Member member = memberUtil.getMember();
            if (isMine && (!memberUtil.isLogin() || !member.getSeq().equals(counseling.getStudentNo()))) {
                throw new UnAuthorizedException();
            }

            return counseling;
        }

        //예약 목록 조회
        public ListData<Counseling> getList(CounselingSearch search) {
            int page = Math.max(search.getPage(), 1);
            // limit 처리
            int limit = search.getLimit() > 0 ? search.getLimit() : 20;
//            limit = limit < 1 ? 20 : limit;

            int offset = (page - 1) * limit;

            String sopt = search.getSopt();
            String skey = search.getSkey();

            LocalDate sDate = search.getStartDate();
            LocalDate eDate = search.getEndDate();

            QCounseling counseling = QCounseling.counseling;
            BooleanBuilder andBuilder = new BooleanBuilder();

            // 학생번호로 조회(본인의 예약 정보만 조회), 근데 멤버키로 해야 상담사도 이걸로 조회 하지 않나?
            List<Long> studentNo = search.getStudentNo();
            if (studentNo != null && !studentNo.isEmpty()) {
                andBuilder.and(counseling.studentNo.in(studentNo));
            }

            String status = search.getStatus();
            status = StringUtils.hasText(status) ? status : "ALL";

            // 상태 필터링 (예약 상태에 따라)
            if (!"ALL".equals(status)) {
                andBuilder.and(counseling.status.eq(Status.valueOf(status)));
            }

            sopt = sopt != null && StringUtils.hasText(sopt.trim()) ? sopt.trim() : "ALL"; //통합 검색
            if (skey != null && StringUtils.hasText(skey.trim())) {
                   skey = skey.trim();
                StringExpression expression = null;
                if (sopt.equals("ALL")) { //통합 검색
                    expression = counseling.email
                            .concat(counseling.mobile)
                            .concat(counseling.empNo)
                            .concat(counseling.cCase.stringValue())
                            .concat(counseling.cReason.stringValue())
                            .concat(String.valueOf(counseling.studentNo));
                } else if (sopt.equals("USERNAME")) {
                    expression = counseling.username;
                } else if (sopt.equals("C_CASE")) {
                    expression = counseling.cCase.stringValue();
                }
                if (expression != null) {
                    andBuilder.and(expression.contains(skey)); //포함 조건
                }
            }


            // 날짜 필터링 (시작일, 종료일)
            if (sDate != null) {
                andBuilder.and(counseling.rDate.goe(sDate));
            }
            if (eDate != null) {
                andBuilder.and(counseling.rDate.loe(eDate));
            }

            //목록 데이터 가져오기
            List<Counseling> items = queryFactory.selectFrom(counseling)
                    .where(andBuilder) // 멤버 기본키로 해야하는데
                    .fetchJoin()
                    .where(andBuilder)
                    .offset(offset)
                    .limit(limit)
                    .orderBy(counseling.createdAt.desc()) // 예약 등록일자 기준 정렬
                    .fetch();

            // 총 레코드 수 계산
            long total = counselingRepository.count(andBuilder);

            // pagination 객체 생성
            Pagination pagination = new Pagination(page, (int) total, 10, limit, request);

            return new ListData<>(items, pagination);
        }

        private void addInfo(Counseling counseling) {

        }


    }
