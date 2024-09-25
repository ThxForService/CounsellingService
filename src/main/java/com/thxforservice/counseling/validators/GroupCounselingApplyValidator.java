package com.thxforservice.counseling.validators;

import com.querydsl.core.BooleanBuilder;
import com.thxforservice.counseling.controllers.RequestGroupCounselingApply;
import com.thxforservice.counseling.entities.QGroupCounseling;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class GroupCounselingApplyValidator implements Validator {

    private final GroupCounselingRepository repository;

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

        if (repository.exists(builder)) {
            errors.reject("Exists");
        }

        //
    }
}
