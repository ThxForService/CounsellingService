package com.thxforservice.counseling.services;

import com.thxforservice.counseling.controllers.RequestGroupCounselingApply;
import com.thxforservice.counseling.entities.GroupCounseling;
import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.exceptions.CounselingNotFoundException;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import com.thxforservice.counseling.repositories.GroupProgramRepository;
import com.thxforservice.member.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupCounselingApplyService { //신청하는 거 + 신청목록 조회 + 신청 상세정보 조회

    private final GroupCounselingRepository counselingRepository;
    private final GroupProgramRepository programRepository;
    private final MemberUtil memberUtil;

    public GroupCounseling apply(RequestGroupCounselingApply form) {

        Long schdlSeq = form.getPgmSeq();

        /**
         * [검증]
         * 1. 신청자가 신청한 프로그램의 스케줄이 존재하는지 검증
         * 2. 해당 스케줄이 예약 마감인지 검증 후. 인원 + 1
         */

        //1. 신청자가 신청한 프로그램의 스케줄이 존재하는지 검증
        //GroupProgram counseling = counselingRepository.findById(schdlSeq)
                .orElseThrow(CounselingNotFoundException::new);

        //2. 해당 스케줄의 신청인원이 꽉차있는지 검증 후. 신청인원 + 1
        int capacity = counseling.getCapacity();
        //if(capacity > 30) throw new CounselingCapacityFullException();
        counseling.setCapacity(capacity + 1);
        counselingRepository.saveAndFlush(counseling);


        GroupCounseling reservation = GroupCounseling.builder()
                .program(counseling)
                .studentNo(form.getStudentNo())
                .username(form.getUsername())
                .grade(form.getGrade())
                .department(form.getDepartment())
                .build();

        programRepository.saveAndFlush(reservation);

        return reservation;
    }
}