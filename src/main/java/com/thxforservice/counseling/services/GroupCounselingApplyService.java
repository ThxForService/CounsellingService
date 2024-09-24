package com.thxforservice.counseling.services;

import com.thxforservice.counseling.controllers.RequestGroupCounselingApply;
import com.thxforservice.counseling.entities.GroupCounseling;
import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.exceptions.GroupProgramNotFoundException;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import com.thxforservice.counseling.repositories.GroupProgramRepository;
import com.thxforservice.member.MemberUtil;
import com.thxforservice.member.entities.Member;
import com.thxforservice.member.entities.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupCounselingApplyService { //신청하는 거 + 신청목록 조회 + 신청 상세정보 조회

    private final GroupProgramRepository programRepository;
    private final MemberUtil memberUtil;
    private final GroupCounselingRepository counselingRepository;

    public GroupCounseling apply(RequestGroupCounselingApply form) {

        /**
         * [검증]
         * 1. 프로그램이 존재하는지 확인
         * 2. 제한인원을 초과했는지 확인
         * 3. 이미 예약을 했는지 확인
         */

        //1. 프로그램이 존재하는지 확인
        GroupProgram program = programRepository.findById(form.getPgmSeq()).orElseThrow(GroupProgramNotFoundException::new);

        //2. 제한인원을 초과했는지 확인
        int capacity = program.getCapacity();
        //int currentCount = counselingResInfoService.count(program.getPgmSeq());
        //if(currentCount >= currentCount) throw new GroupProgramFullException();


        //3. 이미 예약을 했는지 확인(추후 넣어야함)


        Member member = memberUtil.getMember();
        Student student = memberUtil.getMember();


        GroupCounseling groupCounseling = GroupCounseling.builder()
                .program(program)
                .studentNo(student.getStudentNo())
                .username(member.getUserName())
                .grade(student.getGrade())
                .department(student.getDepartment())
                .email(member.getEmail())
                .mobile(member.getMobile()).build();


        counselingRepository.saveAndFlush(groupCounseling);

        programRepository.saveAndFlush(program);


        return groupCounseling;
    }
}
