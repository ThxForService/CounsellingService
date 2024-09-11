package com.thxforservice.counselling.controllers;

import com.thxforservice.counselling.entities.Counselling;
import com.thxforservice.counselling.services.CounsellingApplyService;
import com.thxforservice.counselling.services.CounsellingInfoService;
import com.thxforservice.counselling.validators.CounsellingValidator;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Utils;
import com.thxforservice.global.exceptions.BadRequestException;
import com.thxforservice.global.rests.JSONData;
import com.thxforservice.member.MemberUtil;
import com.thxforservice.member.entities.Member;
import jakarta.validation.Valid;
import jdk.jshell.execution.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/counselling")
public class CounsellingController {

    private final CounsellingInfoService infoService;
    private final MemberUtil memberUtil;
    private final CounsellingValidator validator;
    private final CounsellingApplyService applyService;
    private final Utils utils;

    /**
     * 예약접수
     *
     * @return
     */
    @PostMapping("/apply")
    public ResponseEntity<JSONData> apply(@RequestBody @Valid RequestCounselling form, Errors errors) {

        validator.validate(form,errors); // 예약 검증

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        Counselling counselling = applyService.apply(form);

        HttpStatus status = HttpStatus.CREATED;
        JSONData jsonData = new JSONData(counselling);
        jsonData.setStatus(status);

        return ResponseEntity.status(status).body(jsonData);
    }


    /**
     * 예약 정보 조회
     */
    @GetMapping("/complete/{seq}")
    public JSONData rsvInfo(@PathVariable("seq") Long seq) {
        Counselling counselling = infoService.get(seq);

        return new JSONData(counselling);
    }

    @GetMapping("/list") //예약은 회원가입한 멤버만 예약 조회 가능 -> 회원이 아니면 404
    public JSONData list(CounsellingSearch search) {
        Member member = memberUtil.getMember();
        search.setStudentId(List.of(member.getSeq()));

        ListData<Counselling> data = infoService.getList(search);

        return new JSONData(data);
    }
}
