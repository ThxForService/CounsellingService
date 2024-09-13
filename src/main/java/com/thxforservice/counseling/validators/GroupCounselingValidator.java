package com.thxforservice.counseling.validators;

import com.thxforservice.counseling.controllers.RequestGroupCounselingSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class GroupCounselingValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestGroupCounselingSave.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        RequestGroupCounselingSave form = (RequestGroupCounselingSave) target;
        int capacity = form.getCapacity();

//      1. 집단 상담 프로그램 정원 5명~30명
        if (capacity < 5 || capacity > 30) {
            errors.rejectValue("capacity", "capacity.outOfRange");
        }
    }
}
