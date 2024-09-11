package com.thxforservice.counselling.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counselling.controllers.CounselingSearch;
import com.thxforservice.counselling.entities.Counseling;
import com.thxforservice.counselling.entities.QCounseling;
import com.thxforservice.counselling.exceptions.CounselingNotFoundException;
import com.thxforservice.counselling.repositories.CounsellingRepository;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Pagination;
import com.thxforservice.global.exceptions.UnAuthorizedException;
import com.thxforservice.member.MemberUtil;
import com.thxforservice.member.entities.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CounsellingInfoService {
    private final JPAQueryFactory queryFactory;
    private final CounsellingRepository counsellingRepository;
    private final HttpServletRequest request;
    private final MemberUtil memberUtil;


    /**
     * 상담 예약 상세 정보 조회
     *
     * @param Cseq 예약 고유 번호
     * @return 상담 예약 정보
     */
    public Counseling get(Long Cseq) {
        Counseling counseling = counsellingRepository.findById(Cseq).orElseThrow(CounselingNotFoundException::new);
        return counseling;
    }

    public Counseling get(Long Cseq, boolean isMine) {
        Counseling counseling = get(Cseq);

        Member member = memberUtil.getMember();
        if (isMine && (!memberUtil.isLogin() || !member.getSeq().equals(counseling.getStudentNo()))) {
            throw new UnAuthorizedException();
        }

        return counseling;
    }

    // 예약 목록 조회
    public ListData<Counseling> getList(CounselingSearch search) {
        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 20 : limit;

        int offset = (page - 1) * limit;

        String sopt = search.getSopt();
        String skey = search.getSkey();

        LocalDate sDate = search.getStartDate();
        LocalDate eDate = search.getEndDate();

//        List<Long> studentId = search.getStudentId(); // 회원번호로 조회 // 학번
        Long studentId = search.getStudentId(); // 회원번호로 조회 // 학번

        String status = search.getStatus();
        status = StringUtils.hasText(status) ? status : "ALL";

        // 검색 처리
        QCounseling counselling = QCounseling.counseling;
        BooleanBuilder andBuilder = new BooleanBuilder();

        // 데이터 가져오기
        List<Counseling> items = queryFactory.selectFrom(counselling)
                .where(andBuilder)
                .offset(offset)
                .limit(limit)
                .orderBy(counselling.createdAt.desc())
                .fetch();

        long total = counsellingRepository.count(andBuilder);

        // pagination 객체 생성
        Pagination pagination = new Pagination(page, (int) total, 10, limit, request);

        return new ListData<>(items, pagination);
    }
}
