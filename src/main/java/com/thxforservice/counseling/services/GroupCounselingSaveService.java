package com.thxforservice.counseling.services;

import com.thxforservice.counseling.controllers.RequestGroupCounselingSave;
import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.exceptions.GroupProgramNotFoundException;
import com.thxforservice.counseling.repositories.GroupProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GroupCounselingSaveService {

    private final GroupProgramRepository programRepository;

    // 집단 상담 프로그램 추가
    public void addProgram(RequestGroupCounselingSave form) {

        GroupProgram program = GroupProgram.builder()
                .pgmNm(form.getPgmNm())
                .Description(form.getDescription())
                .pgmStartDate(LocalDateTime.of(form.getProgramStartDate(), form.getProgramStartTime()))
                .startDate(form.getStartDate())
                .endDate(form.getEndDate())
                .capacity(form.getCapacity() == null? 5 : form.getCapacity())
                .status(form.getStatus())
                .empNo(form.getEmpNo())
                .build();

        programRepository.saveAndFlush(program);
    }

    // 집단 상담 프로그램 수정
    public void updateProgram(Long pgmSeq, RequestGroupCounselingSave form) {
        GroupProgram program = programRepository.findById(pgmSeq)
                .orElseThrow(GroupProgramNotFoundException::new);

            program.setPgmNm(form.getPgmNm());

            program.setDescription(form.getDescription());

            program.setStartDate(form.getStartDate());

            program.setEndDate(form.getEndDate());

            if (form.getCapacity() != null) {
                program.setCapacity(Math.min(Math.max(form.getCapacity(), 5), 30));
            }

            program.setStatus(form.getStatus());

        programRepository.saveAndFlush(program);

    }
}

