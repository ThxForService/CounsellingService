package com.thxforservice.counselling.services;

import com.thxforservice.counselling.controllers.RequestGroupCounselingxxxxxx;
import com.thxforservice.counselling.entities.GroupCounseling;
import com.thxforservice.counselling.entities.GroupProgram;
import com.thxforservice.counselling.exceptions.CounselingNotFoundException;
import com.thxforservice.counselling.repositories.GroupCounselingRepository;
import com.thxforservice.counselling.repositories.GroupProgramRepository;
import com.thxforservice.member.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class GroupCounselingApplyService { //신청하는 거 + 신청목록 조회 + 신청 상세정보 조회

    private final GroupCounselingRepository counselingRepository;
    private final GroupProgramRepository programRepository;
    private final MemberUtil memberUtil;

    public GroupProgram apply(RequestGroupCounselingxxxxxx form) {

        Long programId = form.getProgramId();

        GroupCounseling counseling = counselingRepository.findById(programId)
                .orElseThrow(CounselingNotFoundException::new);

//        GroupProgram program = programRepository.findById(programId)
//                .orElseThrow(CounselingNotFoundException::new);

        String username = form.getUsername();
        if (StringUtils.hasText(username)) {
            username = username.trim();
        }


        GroupProgram reservation = GroupProgram.builder()
                .program(counseling)
                .studentNo(form.getStudentNo())
                .username(username)
                .grade(form.getGrade())
                .department(form.getDepartment())
                .attend(form.getAttend())
                .build();

        int newCapacity = Math.min(Math.max(form.getCapacity(), 5), 30);
        counseling.setCapacity(newCapacity);
        counselingRepository.saveAndFlush(counseling);

        programRepository.saveAndFlush(reservation);

        return reservation;
    }
}
