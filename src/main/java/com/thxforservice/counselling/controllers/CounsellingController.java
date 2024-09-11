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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jdk.jshell.execution.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/counselling")
@Tag(name = "Counseling", description = " 상담 API")
public class CounsellingController {
    /**
     *  1. 개인 상담 신청  - POST /apply
     *  2. 집단 상담 하나 정보  - GET /group/info/{pgmSeq}
     *      - 집단 상담 정보(GroupCounseling)
     *          - 그룹 상담 스케줄 목록(GroupSchedule)
     *  3. 집단 상담 목록
     *      - GET /group :
     *
     *  4. 집단 상담 신청 처리
     *          - 신청시 연락처, 이메일은 변경 가능
     *          - POST /group/apply
     *              - 집단 상픔 스케줄 등록 번호(GroupSchedule)
     *              - 로그인한 회원의 학번
     *              - 입력한 email, mobile이 필요
     *              - 신청 가능 여부 체크 필요
     *
     *  5. 개인 상담 일정 변경
     *      - 편성된 상담은 empNo로 조회된 상담
     *      - 편성된 상담 일정 목록  GET /cs/list
     *      - 편성된 상담 하나 조회  GET /cs/info/{cSeq}
     *      - 편성된 상담 변경 처리  PATCH /cs/change
     *
     *      - 상담사 : 상담사로 권한 제한
     *      - rDate, rTime
     */
/* 강사님 코드 S */
    @Operation(summary = "개인 상담 신청", method = "POST")
    @ApiResponse(responseCode = "201")
    @PostMapping("/apply")
    public ResponseEntity<Void> apply(){

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

/* 강사님 코드 E */
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

        validator.validate(form, errors); // 예약 검증

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
