package com.thxforservice;

import com.thxforservice.counseling.constants.ProgramStatus;
import com.thxforservice.counseling.controllers.RequestGroupCounselingSave;
import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.exceptions.CounselingNotFoundException;
import com.thxforservice.counseling.services.GroupCounselingSaveService;
import com.thxforservice.counseling.validators.GroupCounselingValidator;
import com.thxforservice.global.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.time.LocalDate;

@SpringBootTest
public class GroupProgramSaveTest {

   @Autowired
   private GroupCounselingSaveService counselingSaveService;

   @Autowired
   private GroupCounselingValidator validator;

   private Errors errors;

    @Test
    void testAddProgram() {

        RequestGroupCounselingSave form = new RequestGroupCounselingSave();
        form.setPgmNm("Test Program");
        form.setDescription("Test Description");
        form.setStartDate(LocalDate.of(2024, 9, 12));
        form.setEndDate(LocalDate.of(2024, 9, 15));
        form.setCapacity(50);
        form.setStatus(ProgramStatus.READY);

        counselingSaveService.addProgram(form);
    }
}
