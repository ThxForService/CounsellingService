package com.thxforservice.counseling.validators;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counseling.controllers.RequestCounselingApply;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.entities.QCounseling;
import com.thxforservice.counseling.repositories.CounselingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CounselingValidator implements Validator {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Counseling.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // 커맨드 객체를 타겟으로 지정
        RequestCounselingApply form = (RequestCounselingApply) target;

        // 예약일, 시간 검증 S
        QCounseling counseling = QCounseling.counseling;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(counseling.rDate.eq(form.getRDate()))
                .and(counseling.rTime.eq(form.getRTime()));

        List<Counseling> dateVerification = queryFactory.selectFrom(counseling)
                .where(builder)
                .fetch();
        // 예약일 검증 E

        if (!dateVerification.isEmpty()) {
            // 중복인 경우 오류 처리
            errors.rejectValue("rTime", "duplicate.counseling", "해당 시간대에 이미 예약이 존재합니다.");
        }
    }
}

