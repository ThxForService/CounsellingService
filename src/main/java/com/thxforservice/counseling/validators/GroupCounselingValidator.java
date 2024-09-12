package com.thxforservice.counseling.validators;

import com.thxforservice.counseling.entities.GroupCounseling;
import com.thxforservice.counseling.entities.GroupProgram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class GroupCounselingValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(GroupProgram.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        GroupProgram form = (GroupProgram) target;
        int capacity = form.getCapacity();

//      1. 집단 상담 프로그램 정원 5명~30명
        if (capacity < 5 || capacity > 30) {
            errors.rejectValue("capacity", "capacity.outOfRange", "프로그램 정원은 5명 이상 30명 이하여야만 합니다.");
        }
    }
}
