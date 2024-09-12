package com.thxforservice.counselling.services;

import com.thxforservice.counselling.controllers.RequestGroupCounselingSave;
import com.thxforservice.counselling.entities.GroupCounseling;
import com.thxforservice.counselling.exceptions.CounselingNotFoundException;
import com.thxforservice.counselling.repositories.GroupCounselingRepository;
import com.thxforservice.counselling.repositories.GroupProgramRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class GroupCounselingSaveService {

    private final GroupCounselingRepository counselingRepository;
    private final GroupProgramRepository programRepository;

    // 집단 상담 프로그램 추가
    public void addProgram(RequestGroupCounselingSave form) {

        GroupCounseling program = GroupCounseling.builder()
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
