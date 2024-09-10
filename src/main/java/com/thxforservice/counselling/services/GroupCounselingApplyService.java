package com.thxforservice.counselling.services;

import com.thxforservice.counselling.controllers.RequestGroupCounseling;
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
public class GroupCounselingApplyService {

    private final GroupCounselingRepository counselingRepository;
    private final GroupProgramRepository programRepository;
    private final MemberUtil memberUtil;

    public GroupProgram apply(RequestGroupCounseling form) {

        Long programId = form.getProgramId();
        GroupProgram program = programRepository.findById(programId)
                .orElseThrow(CounselingNotFoundException::new);

        String username = form.getUsername();
        if (StringUtils.hasText(username)) {
            username = username.trim();
        }

        GroupProgram reservation = GroupProgram.builder()
                .program(program)
                .studentNo(form.getStudentNo())
                .username(username)
                .grade(form.getGrade())
                .department(form.getDepartment())
                .attend(form.getAttend())
                .capacity(Math.min(Math.max(form.getCapacity(), 5), 30))
                .build();

        programRepository.saveAndFlush(reservation);

        return reservation;
    }
}
