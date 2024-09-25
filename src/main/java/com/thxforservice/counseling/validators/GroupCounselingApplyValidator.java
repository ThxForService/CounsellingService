package com.thxforservice.counseling.validators;

import com.querydsl.core.BooleanBuilder;
import com.thxforservice.counseling.controllers.RequestGroupCounselingApply;
import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.entities.QGroupCounseling;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import com.thxforservice.counseling.repositories.GroupProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class GroupCounselingApplyValidator implements Validator {

    private final GroupCounselingRepository counselingRepository;
    private final GroupProgramRepository programRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestGroupCounselingApply.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        // 한 사람의 중복 신청 통제
        RequestGroupCounselingApply form = (RequestGroupCounselingApply) target;
        Long pgmSeq = form.getPgmSeq();
        Long studentNo = form.getStudentNo();

        QGroupCounseling groupCounseling = QGroupCounseling.groupCounseling;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(groupCounseling.studentNo.eq(studentNo))
                .and(groupCounseling.program.pgmSeq.eq(pgmSeq));

        if (counselingRepository.exists(builder)) {
            errors.reject("Exists");
        }

        // 신청기간에 따라 신청 여부 통제
        LocalDate now = LocalDate.now();
        GroupProgram program = programRepository.findById(pgmSeq).orElse(null);

        if (program != null) {
            if (now.isBefore(program.getStartDate())) {
                errors.reject("NotInApplicationPeriod");
            } else if (now.isAfter(program.getEndDate())) {
                errors.reject("NotInApplicationPeriod");
            }

            // 신청 정원
            long currentCount = counselingRepository.count(groupCounseling.program.pgmSeq.eq(pgmSeq));

            int capacity = program != null ? program.getCapacity() : 0;

            if (currentCount > capacity) {
                errors.reject("CapacityExceeded");
            }
        }
    }
}

