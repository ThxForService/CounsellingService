package com.thxforservice.counseling;


import com.thxforservice.counseling.constants.CCase;
import com.thxforservice.counseling.constants.CReason;
import com.thxforservice.counseling.controllers.RequestCounselingApply;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.repositories.CounselingRepository;
import com.thxforservice.counseling.services.CounselingApplyService;
import com.thxforservice.global.configs.CorsFilterConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Transactional
public class ApplyTest {

    @Autowired
    private CounselingApplyService applyService;

    @Test
    @DisplayName("예약 신청 테스트")
    void test01() {

        RequestCounselingApply form = new RequestCounselingApply();
        form.setStudentNo(1234L);
        form.setEmail("test01@test.org");
        form.setMobile("01011112222");
        form.setUsername("윤다은");
        form.setGrade("6학년 4반");
        form.setDepartment("심신한데뽀뽀할과");
        form.setCReason(CReason.VOLUNTARY);
        form.setCCase(CCase.PSYCHOLOGICAL);
        form.setRDate(LocalDate.of(2024, 8, 29));
        System.out.println(form);

        Counseling counseling = applyService.apply(form);
        System.out.println(counseling);

    }
}
