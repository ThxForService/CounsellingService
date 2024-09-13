package com.thxforservice.counseling.services;

import com.querydsl.core.BooleanBuilder;
import com.thxforservice.counseling.controllers.GroupCounselingSearch;
import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.entities.QGroupProgram;
import com.thxforservice.counseling.exceptions.CounselingNotFoundException;
import com.thxforservice.counseling.repositories.GroupProgramRepository;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Pagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupCounselingInfoService {

    private final GroupProgramRepository programRepository;
    private final HttpServletRequest request;

    /**
     * 프로그램 상세 정보
     *
     * @param id
     * @return
     */
    public GroupProgram get(Long id) {
        GroupProgram counseling = programRepository.findById(id)
                .orElseThrow(CounselingNotFoundException::new);

        //추가 정보 처리
        addInfo(counseling);

        return counseling;
    }

    /**
     * 집단 상담 프로그램 목록 조회,
     *      페이지네이션
     *
     * @param search
     * @return
     */
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

        LocalDateTime programStartDate = search.getProgramStartDate();

        if (programStartDate != null) {
            andBuilder.and(groupProgram.pgmStartDate.goe(programStartDate));
        }

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt")));

        Page<GroupProgram> data = programRepository.findAll(andBuilder, pageable);

        Pagination pagination = new Pagination(page, (int) data.getTotalElements(), 10, limit, request);

        return new ListData<>(data.getContent(), pagination);
    }


    private void addInfo(GroupProgram counseling) {

    }
}
