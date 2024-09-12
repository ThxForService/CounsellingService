package com.thxforservice.counselling.services;

import com.thxforservice.counselling.controllers.RequestGroupCounselingSave;
import com.thxforservice.counselling.entities.GroupProgram;
import com.thxforservice.counselling.repositories.GroupCounselingRepository;
import com.thxforservice.counselling.repositories.GroupProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupCounselingSaveService {

    private final GroupCounselingRepository counselingRepository;
    private final GroupProgramRepository programRepository;

    // 집단 상담 프로그램 추가
    public void addProgram(RequestGroupCounselingSave form) {

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
