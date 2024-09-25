package com.thxforservice.counseling.validators;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counseling.controllers.RequestCounselingApply;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.entities.QCounseling;
import com.thxforservice.counseling.repositories.CounselingRepository;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
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
    private final GroupCounselingRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Counseling.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // 커맨드 객체를 타겟으로 지정
        RequestCounselingApply form = (RequestCounselingApply) target;

        // 예약일이 현재보다 과거인지 확인
        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();


        String errorCode = "NotAvailable.reservation";
        // 예약일 검증: 과거 날짜에 예약 불가
        if (form.getRDate().isBefore(today)) {
            errors.rejectValue("rDate", errorCode);
        }

        // 예약일이 오늘인 경우, 예약 시간이 현재 시간 이후여야 함
        if (form.getRDate().isEqual(today) && form.getRTime().isBefore(currentTime)) {
            errors.rejectValue("rTime",errorCode);
        }

        // 중복 예약 검증
        QCounseling counseling = QCounseling.counseling;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(counseling.rDate.eq(form.getRDate()))
                .and(counseling.rTime.eq(form.getRTime()))
                .and(counseling.studentNo.eq(form.getStudentNo()));

        List<Counseling> dateVerification = queryFactory.selectFrom(counseling)
                .where(builder)
                .fetch();

        if (!dateVerification.isEmpty()) {
            // 중복인 경우 오류 처리
            errors.rejectValue("rTime", errorCode);
        }
    }
}

