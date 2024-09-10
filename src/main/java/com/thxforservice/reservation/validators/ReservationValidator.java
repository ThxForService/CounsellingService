package com.thxforservice.reservation.validators;

import com.thxforservice.reservation.entities.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ReservationValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Reservation.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        // 커맨드 객체를 타겟으로 지정
        // 예약 검증 가능한 키 담기


        // 예약일 검증 S

        // 예약일 검증 E


    }

}

