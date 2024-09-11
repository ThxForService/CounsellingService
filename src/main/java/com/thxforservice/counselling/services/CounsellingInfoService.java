package com.thxforservice.counselling.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counselling.constants.Status;
import com.thxforservice.counselling.controllers.CounsellingSearch;
import com.thxforservice.counselling.entities.Counselling;
import com.thxforservice.counselling.entities.QCounselling;
import com.thxforservice.counselling.exceptions.CounsellingNotFoundException;
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

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class CounsellingInfoService {
    private final JPAQueryFactory queryFactory;
    private final CounsellingRepository counsellingRepository;
    private final HttpServletRequest request;
    private final MemberUtil memberUtil;
    private final CounsellingInfoService counsellingInfoService;


    /**
     * 상담 예약 상세 정보 조회
     *
     * @param Cseq 예약 고유 번호
     * @return 상담 예약 정보
     */
    public Counselling get(Long Cseq) {
        Counselling counselling = counsellingRepository.findById(Cseq).orElseThrow(CounsellingNotFoundException::new);
        return counselling;
    }

    public Counselling get(Long Cseq, boolean isMine) {
        Counselling counselling = get(Cseq);

        Member member = memberUtil.getMember();
        if (isMine && (!memberUtil.isLogin() || !member.getSeq().equals(counselling.getMemberID()))) {
            throw new UnAuthorizedException();
        }

        return counselling;
    }

    // 예약 목록 조회
    public ListData<Counselling> getList(CounsellingSearch search) {
        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 20 : limit;

        int offset = (page - 1) * limit;

        String sopt = search.getSopt();
        String skey = search.getSkey();

        LocalDate sDate = search.getSDate();
        LocalDate eDate = search.getEDate();

        List<Long> memberSeqs = search.getStudentId(); // 회원번호로 조회 // 학번

        String status = search.getStatus();
        status = StringUtils.hasText(status) ? status : "ALL";

        // 검색 처리
        QCounselling counselling = QCounselling.counselling;
        BooleanBuilder andBuilder = new BooleanBuilder();

        // 데이터 가져오기
        List<Counselling> items = queryFactory.selectFrom(counselling)
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
