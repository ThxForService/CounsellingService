package com.thxforservice.counseling.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counseling.controllers.RequestGroupCounselingSave;
import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.exceptions.CounselingNotFoundException;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import com.thxforservice.counseling.repositories.GroupProgramRepository;
import com.thxforservice.counseling.validators.GroupCounselingValidator;
import com.thxforservice.global.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupCounselingSaveService {

    private final GroupCounselingValidator validator;
    private final JPAQueryFactory queryFactory;
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

        counselingRepository.saveAndFlush(program);
    }

    // 집단 상담 프로그램 수정
    public void updateProgram(Long pgmSeq, RequestGroupCounselingSave form) {
        GroupProgram program = counselingRepository.findById(pgmSeq)
                .orElseThrow(CounselingNotFoundException::new);

            program.setPgmNm(form.getPgmNm());

            program.setDescription(form.getDescription());

            program.setStartDate(form.getStartDate());

            program.setEndDate(form.getEndDate());

            if (form.getCapacity() != null) {
                program.setCapacity(Math.min(Math.max(form.getCapacity(), 5), 30));
            }

            program.setStatus(form.getStatus());

        counselingRepository.saveAndFlush(program);

    }
}

