package com.thxforservice.counseling.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counseling.controllers.GroupCounselingSearch;
import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.entities.QGroupCounseling;
import com.thxforservice.counseling.entities.QGroupProgram;
import com.thxforservice.counseling.exceptions.CounselingNotFoundException;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Pagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupCounselingInfoService {

    private final JPAQueryFactory queryFactory;
    private final GroupCounselingRepository counselingRepository;
    private final HttpServletRequest request;

    /**
     * 프로그램 상세 정보
     *
     * @param id
     * @return
     */
    public GroupProgram get(Long id) {
        GroupProgram counseling = counselingRepository.findById(id)
                .orElseThrow(CounselingNotFoundException::new);

        //추가 정보 처리
        addInfo(counseling);

        return counseling;
    }

    // 집단 상담 프로그램 목록 + 페이지네이션
    public ListData<GroupProgram> getGroupCounselingList(GroupCounselingSearch search) {

        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 10 : limit;
        int offset = (page - 1) * limit;

        BooleanBuilder andBuilder = new BooleanBuilder();
        QGroupProgram groupProgram = QGroupProgram.groupProgram;

        String skey = search.getSkey(); // 검색 키워드
        if (StringUtils.hasText(skey)) {
            andBuilder.and(groupProgram.pgmNm.contains(skey.trim()));
        }

        List<Long> pgmSeq = search.getPgmSeq();
        if (pgmSeq != null && !pgmSeq.isEmpty()) {
            andBuilder.and(groupProgram.pgmSeq.in(pgmSeq));
        }


        List<GroupProgram> items = queryFactory.selectFrom(groupProgram)
                .where(andBuilder)
                .fetchJoin()
                .offset(offset)
                .limit(limit)
                .orderBy(groupProgram.createdAt.desc())
                .fetch();

        long total = counselingRepository.count(andBuilder);
        int ranges = 10;
        Pagination pagination = new Pagination(page, (int) total, ranges, limit, request);

        return new ListData<>(items, pagination);
    }


    private void addInfo(GroupProgram counseling) {

    }
}
