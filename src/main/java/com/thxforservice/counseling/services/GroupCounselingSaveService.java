package com.thxforservice.counseling.services;

import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import com.thxforservice.counseling.repositories.GroupProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupCounselingSaveService {

    private final GroupCounselingRepository counselingRepository;
    private final GroupProgramRepository programRepository;

    // 집단 상담 프로그램 추가
    public void addProgram(com.thxforservice.counseling.controllers.RequestGroupCounselingSave form) {

        GroupProgram program = GroupProgram.builder()
                .pgmNm(form.getPgmNm())
                .Description(form.getDescription())
                .startDate(form.getStartDate())
                .endDate(form.getEndDate())
                .capacity(Math.min(Math.max(form.getCapacity(), 5), 30))
                .status(form.getStatus())
                .build();

        counselingRepository.save(program);
    }

}
