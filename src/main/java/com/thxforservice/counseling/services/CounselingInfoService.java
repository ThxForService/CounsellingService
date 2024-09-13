package com.thxforservice.counseling.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counseling.constants.Status;
import com.thxforservice.counseling.controllers.CounselingSearch;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.entities.GroupProgram;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
@Transactional
public class CounselingInfoService {

    private final CounselingRepository repository;
    private final HttpServletRequest request;
    private final MemberUtil memberUtil;

    /**
     * 예약 상세 정보 조회
     *
     * @param cSeq
     * @return
     */
    public Counseling get(Long cSeq) {
        Counseling counseling = repository.findById(cSeq)
                .orElseThrow(CounselingNotFoundException::new);

        //추가 정보 처리
        addInfo(counseling);

        return counseling;
    }

    public Counseling get(Long cSeq, boolean isMine) {
        Counseling counseling = get(cSeq);

        Member member = memberUtil.getMember();
        if (isMine && (!memberUtil.isLogin() || !member.getMemberSeq().equals(counseling.getStudentNo()))) {
            throw new UnAuthorizedException();
        }

        return counseling;
    }

    /**
     * 예약 신청 목록 조회
     *
     * @param search
     * @return
     */
    public ListData<Counseling> getList(CounselingSearch search) {
        int page = Math.max(search.getPage(), 1);
//        int limit = search.getLimit();
//        limit = limit < 1 ? 10 : limit;
        int limit = search.getLimit() > 0 ? 10 : search.getLimit();

        BooleanBuilder andBuilder = new BooleanBuilder();
        QCounseling counseling = QCounseling.counseling;

        String skey = search.getSkey(); // 검색 키워드
        if (StringUtils.hasText(skey)) {
            andBuilder.and(counseling.cSeq.eq(Long.valueOf(skey.trim())));
        }

        List<Long> cSeq = search.getCSeq();
        if (cSeq != null && !cSeq.isEmpty()) {
            andBuilder.and(counseling.cSeq.in(cSeq));
        }

        LocalDate startDate = search.getStartDate();

        if (startDate != null) {
            andBuilder.and(counseling.rDate.goe(startDate));
        }

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt")));

        Page<Counseling> data = repository.findAll(andBuilder, pageable);

        Pagination pagination = new Pagination(page, (int) data.getTotalElements(), 10, limit, request);

        return new ListData<>(data.getContent(), pagination);
    }

    private void addInfo(Counseling counseling) {

    }


}
