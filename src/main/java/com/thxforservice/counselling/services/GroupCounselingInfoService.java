package com.thxforservice.counselling.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counselling.controllers.GroupCounselingSearch;
import com.thxforservice.counselling.entities.*;
import com.thxforservice.counselling.repositories.GroupCounselingRepository;
import com.thxforservice.counselling.repositories.GroupProgramRepository;
import com.thxforservice.counselling.repositories.GroupScheduleRepository;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Pagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.GroupDefinitionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupCounselingInfoService {

    private final JPAQueryFactory queryFactory;
    private final GroupCounselingRepository counselingRepository;
    private final GroupProgramRepository programRepository;
    private final GroupScheduleRepository scheduleRepository;
    private final HttpServletRequest request;

    /**
     * 그룹 상담 프로그램 상세 정보 조회
     *
     * @param programId
     * @return
     */
    public GroupCounseling get(Long programId) {
        GroupCounseling counseling = counselingRepository.findById(programId)
                .orElseThrow(GroupDefinitionException::new);

        // 추가 정보 처리
        addCounselingInfo(counseling);

        return counseling;
    }

    private void addCounselingInfo(GroupCounseling counseling) {

    }

    /**
     * 그룹 프로그램 상세 정보 조회
     *
     * @param pgmRegSeq
     * @return
     */
    public GroupProgram getProgram(Long pgmRegSeq) {
        GroupProgram program = programRepository.findById(pgmRegSeq)
                .orElseThrow(() -> new RuntimeException("Program registration not found"));

        // 추가 정보 처리
        addProgramInfo(program);

        return program;
    }

    /**
     * 그룹 일정 상세 정보 조회
     *
     * @param scheduleId
     * @return
     */
    public GroupSchedule getSchedule(Long scheduleId) {
        GroupSchedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        // 추가 정보 처리
        addScheduleInfo(schedule);

        return schedule;
    }

    /**
     * 그룹 상담 프로그램 목록 조회
     *
     * @param search
     * @return
     */
    public ListData<GroupCounseling> getCounselingList(GroupCounselingSearch search) {
        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 20 : limit;

        int offset = (page - 1) * limit;

        String sopt = search.getSopt();
        String skey = search.getSkey();

        LocalDate sDate = search.getStartDate();
        LocalDate eDate = search.getEndDate();

        /* 검색 처리 S */
        QGroupCounseling counseling = QGroupCounseling.groupCounseling;
        BooleanBuilder andBuilder = new BooleanBuilder();

        sopt = sopt != null && StringUtils.hasText(sopt.trim()) ? sopt.trim() : "ALL";
        if (skey != null && StringUtils.hasText(skey.trim())) {
            StringExpression expression = null;
            if (sopt.equals("ALL")) { // 통합 검색
                expression = counseling.pgmNm.concat(counseling.Description);
            } else if (sopt.equals("NAME")) {
                expression = counseling.pgmNm;
            } else if (sopt.equals("DESCRIPTION")) {
                expression = counseling.Description;
            }

            if (expression != null) {
                andBuilder.and(expression.contains(skey.trim()));
            }
        }

        // 기간 검색
        if (sDate != null) {
            andBuilder.and(counseling.startDate.goe(sDate));
        }

        if (eDate != null) {
            andBuilder.and(counseling.endDate.loe(eDate));
        }

        List<GroupCounseling> items = queryFactory.selectFrom(counseling)
                .where(andBuilder)
                .offset(offset)
                .limit(limit)
                .orderBy(counseling.startDate.desc())
                .fetch();

        long total = counselingRepository.count(andBuilder);

        Pagination pagination = new Pagination(page, (int)total, 10, limit, request);

        return new ListData<>(items, pagination);
    }

    /**
     * 그룹 프로그램 목록 조회
     *
     * @param searchCriteria
     * @return
     */
    public ListData<GroupProgram> getProgramList(ProgramSearchCriteria searchCriteria) {
        int page = Math.max(searchCriteria.getPage(), 1);
        int limit = searchCriteria.getLimit();
        limit = limit < 1 ? 20 : limit;

        int offset = (page - 1) * limit;

        String sopt = searchCriteria.getSopt();
        String skey = searchCriteria.getSkey();

        LocalDate sDate = searchCriteria.getSDate();
        LocalDate eDate = searchCriteria.getEDate();

        /* 검색 처리 S */
        QGroupProgram program = QGroupProgram.groupProgram;
        BooleanBuilder andBuilder = new BooleanBuilder();

        sopt = sopt != null && StringUtils.hasText(sopt.trim()) ? sopt.trim() : "ALL";
        if (skey != null && StringUtils.hasText(skey.trim())) {
            StringExpression expression = null;
            if (sopt.equals("ALL")) { // 통합 검색
                expression = program.pgmNm.concat(program.Description);
            } else if (sopt.equals("NAME")) {
                expression = program.pgmNm;
            } else if (sopt.equals("DESCRIPTION")) {
                expression = program.Description;
            }

            if (expression != null) {
                andBuilder.and(expression.contains(skey.trim()));
            }
        }

        // 기간 검색
        if (sDate != null) {
            andBuilder.and(program.startDate.goe(sDate));
        }

        if (eDate != null) {
            andBuilder.and(program.endDate.loe(eDate));
        }

        List<GroupProgram> items = queryFactory.selectFrom(program)
                .where(andBuilder)
                .offset(offset)
                .limit(limit)
                .orderBy(program.startDate.desc())
                .fetch();

        long total = programRepository.count(andBuilder);

        Pagination pagination = new Pagination(page, (int)total, 10, limit, request);

        return new ListData<>(items, pagination);
    }

    /**
     * 그룹 일정 목록 조회
     *
     * @param searchCriteria
     * @return
     */
    public ListData<GroupSchedule> getScheduleList(ScheduleSearchCriteria searchCriteria) {
        int page = Math.max(searchCriteria.getPage(), 1);
        int limit = searchCriteria.getLimit();
        limit = limit < 1 ? 20 : limit;

        int offset = (page - 1) * limit;

        String sopt = searchCriteria.getSopt();
        String skey = searchCriteria.getSkey();

        LocalDate sDate = searchCriteria.getSDate();
        LocalDate eDate = searchCriteria.getEDate();

        /* 검색 처리 S */
        QGroupSchedule schedule = QGroupSchedule.groupSchedule;
        BooleanBuilder andBuilder = new BooleanBuilder();

        sopt = sopt != null && StringUtils.hasText(sopt.trim()) ? sopt.trim() : "ALL";
        if (skey != null && StringUtils.hasText(skey.trim())) {
            StringExpression expression = null;
            if (sopt.equals("ALL")) { // 통합 검색
                expression = schedule.memo;
            } else if (sopt.equals("MEMO")) {
                expression = schedule.memo;
            }

            if (expression != null) {
                andBuilder.and(expression.contains(skey.trim()));
            }
        }

        // 날짜 검색
        if (sDate != null) {
            andBuilder.and(schedule.date.goe(sDate));
        }

        if (eDate != null) {
            andBuilder.and(schedule.date.loe(eDate));
        }

        List<GroupSchedule> items = queryFactory.selectFrom(schedule)
                .where(andBuilder)
                .offset(offset)
                .limit(limit)
                .orderBy(schedule.date.desc())
                .fetch();

        long total = scheduleRepository.count(andBuilder);

        Pagination pagination = new Pagination(page, (int)total, 10, limit, request);

        return new ListData<>(items, pagination);
    }

}

