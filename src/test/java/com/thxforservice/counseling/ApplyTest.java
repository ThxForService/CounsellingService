package com.thxforservice.counseling;


import com.netflix.discovery.converters.Auto;
import com.thxforservice.counseling.constants.CCase;
import com.thxforservice.counseling.constants.CReason;
import com.thxforservice.counseling.constants.Status;
import com.thxforservice.counseling.controllers.RequestCounselingApply;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.repositories.CounselingRepository;
import com.thxforservice.counseling.services.CounselingApplyService;
import com.thxforservice.counseling.services.CounselingInfoService;
import com.thxforservice.counseling.validators.CounselingValidator;
import com.thxforservice.global.configs.CorsFilterConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class ApplyTest {

    @Autowired
    private CounselingValidator validator;

    @Autowired
    private CounselingInfoService infoService;

    @Autowired
    private CounselingApplyService applyService;


    @Test
    @DisplayName("중복 예약 검증 테스트")

    void testDuplicateReservation() {
        RequestCounselingApply form = new RequestCounselingApply();
        form.setRDate(LocalDate.of(2024, 10, 1));
        form.setRTime(LocalTime.of(10, 0));
        form.setStudentNo(1234L);

        Errors errors = new BeanPropertyBindingResult(form, "form");
        validator.validate(form, errors);

        assertTrue(errors.hasErrors()); // 중복 예약이 있을 경우, 오류가 발생해야 함
    }

    @Test
    @DisplayName("상담 예약 테스트")
    void testApply() {
        RequestCounselingApply form = new RequestCounselingApply();
        form.setStudentNo(1234L);
        form.setEmail("test@test.org");
        form.setMobile("01011112222");
        form.setUsername("홍길동");
        form.setRDate(LocalDate.of(2024, 10, 1));
        form.setRTime(LocalTime.of(10, 0));
        form.setCCase(CCase.PSYCHOLOGICAL);
        form.setCReason(CReason.VOLUNTARY);

        Counseling counseling = applyService.apply(form);

        assertNotNull(counseling);  // 예약이 성공적으로 생성되었는지 확인
        assertEquals(Status.APPLY, counseling.getStatus()); // 예약 상태가 APPLY로 변경되었는지 확인
    }
}
