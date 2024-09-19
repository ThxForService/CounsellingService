package com.thxforservice.counseling.services;

import com.querydsl.core.BooleanBuilder;
import com.thxforservice.counseling.controllers.CounselingSearch;
import com.thxforservice.counseling.controllers.GroupCounselingSearch;
import com.thxforservice.counseling.entities.GroupCounseling;
import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.entities.QGroupCounseling;
import com.thxforservice.counseling.entities.QGroupProgram;
import com.thxforservice.counseling.exceptions.CounselingNotFoundException;
import com.thxforservice.counseling.exceptions.GroupCounselingNotFoundException;
import com.thxforservice.counseling.exceptions.GroupProgramNotFoundException;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import com.thxforservice.counseling.repositories.GroupProgramRepository;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupCounselingInfoService {

    private final GroupCounselingApplyService applyService;
    private final GroupProgramRepository programRepository;
    private final GroupCounselingRepository counselingRepository;
    private final HttpServletRequest request;
    private final MemberUtil memberUtil;

    /**
     * 프로그램 상세 정보
     *
     * @param pgmSeq
     * @return
     */
    public GroupProgram getProgram(Long pgmSeq) {
        GroupProgram program = programRepository.findById(pgmSeq)
                .orElseThrow(GroupProgramNotFoundException::new);

        //추가 정보 처리
        addInfo(program);

        return program;
    }

    public GroupCounseling getCounseling(Long pgmRegSeq) {
        GroupCounseling counseling = counselingRepository.findById(pgmRegSeq)
                .orElseThrow(GroupCounselingNotFoundException::new);

        //추가 정보 처리
        addInfoCounseling(counseling);

        return counseling;
    }

    private void addInfo(GroupProgram program) {

    }

    private void addInfoCounseling(GroupCounseling counseling) {

    }

    // 집단 상담 프로그램 목록 + 페이지네이션 + 검색
    // 검색 가능 키워드 : 프로그램 이름, 프로그램 진행일
    public ListData<GroupProgram> getGroupProgramList(GroupCounselingSearch search) {

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

    //집단 상담 예약 건에 대한 목록 - 학번으로 검색, 신청번호로 조회
    public ListData<GroupCounseling> getGroupCounselingList(GroupCounselingSearch search) {

        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 10 : limit;
        int offset = (page - 1) * limit;

        BooleanBuilder andBuilder = new BooleanBuilder();
        QGroupCounseling groupCounseling = QGroupCounseling.groupCounseling;

        List<String> email = search.getEmail();
        if (email != null && !email.isEmpty()) {
            andBuilder.and(groupCounseling.email.in(email));
        }

        String skey = search.getSkey(); // 검색 키워드
        if (StringUtils.hasText(skey)) {
            andBuilder.and(groupCounseling.studentNo.like("%" + skey + "%"));
        }

        List<Long> pgmSeq = search.getPgmSeq(); // 신청 번호로 조회
        if (pgmSeq != null && !pgmSeq.isEmpty()) {
            andBuilder.and(groupCounseling.pgmRegSeq.in(pgmSeq));
        }

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt")));

        Page<GroupCounseling> data = counselingRepository.findAll(andBuilder, pageable);

        Pagination pagination = new Pagination(page, (int) data.getTotalElements(), 10, limit, request);

        return new ListData<>(data.getContent(), pagination);
    }

    // 집단 상담 프로그램 삭제
    public GroupProgram deleteProgram(Long pgmSeq) {
        GroupProgram program = getProgram(pgmSeq);
        program.setDeletedAt(LocalDateTime.now());

        programRepository.saveAndFlush(program);

        return program;
    }

/* 상담사 S */

    public GroupCounseling getGroupCounselingById(Long pgmRegSeq) {
        GroupCounseling counseling = counselingRepository.findById(pgmRegSeq)
                .orElseThrow(GroupCounselingNotFoundException::new);

        //추가 정보 처리
        addCounselorInfo(counseling);

        return counseling;
    }

    private void addCounselorInfo(GroupCounseling counseling) {

    }

    // 편성된 프로그램의 신청내역 목록(프로그램번호를 경로변수로 받아서 조회)
    public ListData<GroupCounseling> getCounselorGroupList(GroupCounselingSearch search) {

        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 10 : limit;

        BooleanBuilder andBuilder = new BooleanBuilder();
        QGroupCounseling groupCounseling = QGroupCounseling.groupCounseling;

        List<Long> pgmSeqList = search.getPgmSeq();
        if (pgmSeqList != null && !pgmSeqList.isEmpty()) {
            andBuilder.and(groupCounseling.pgmRegSeq.in(pgmSeqList));
        }

        String skey = search.getSkey(); // 검색 키워드
        if (StringUtils.hasText(skey)) {
            andBuilder.and(groupCounseling.studentNo.like("%" + skey + "%"));
        }

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Order.desc("createdAt")));
        Page<GroupCounseling> data = counselingRepository.findAll(andBuilder, pageable);

        Pagination pagination = new Pagination(page, (int) data.getTotalElements(), 10, limit, request);

        return new ListData<>(data.getContent(), pagination);
    }


/* 상담사 E */
}