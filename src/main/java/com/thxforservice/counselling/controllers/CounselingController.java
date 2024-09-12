package com.thxforservice.counselling.controllers;

import com.thxforservice.counselling.services.GroupCounselingApplyService;
import com.thxforservice.global.Utils;
import com.thxforservice.global.exceptions.BadRequestException;
import com.thxforservice.global.rests.JSONData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Tag(name="Counseling", description = "상담 API")
@RestController
@RequiredArgsConstructor
public class CounselingController {

    private GroupCounselingApplyService groupCounselingApplyService;

    private final Utils utils;
    /**
     *  1. 개인 상담 신청  - POST /apply
     *
     * ------사용자---------
     * 1. 집단 상담 프로그램 신청(예약) - POST program/apply
     * 2. 집단 상담 프로그램 조회(단일)(every)  - GET program/info/{pgmSeq}
     *    집단 상담 프로그램 조회(다중)(every)  - GET program/info
     *
     * 3. 집단 상담 프로그램 취소(사용자) - DELETE program/cancel/{pgmRegSeq}
     *
     * 4. 집단 상담 예약 조회(사용자)(다중) - GET program/res/info
     *
     * -------상담사 -------
     *
     * - 편성된 프로그램의 신청내역 다중조회(목록)  - GET /cs/group/list
     *      *      - 편성된 프로그램의 신청내역 단일조회 - GET /cs/group/info/{schdlSeq}
     *      *      - 편성된 프로그램의 변경 처리(참석 여부, 일지) - PATCH /cs/group/change
     *      *          - 참여율은 자동 계산
     *----------------------
     *
     *  5. 개인 상담 일정 변경 (상담사)
     *      - 편성된 상담은 empNo로 조회된 상담
     *      - 편성된 상담 일정 목록  GET /cs/list
     *      - 편성된 상담 하나 조회  GET /cs/info/{cSeq}
     *      - 편성된 상담 변경 처리(일정, 일지)  PATCH /cs/change
     *
     *      - 상담사 : 상담사로 권한 제한
     *      - rDate, rTime
     *
     */
    @Operation(summary = "개인 상담 신청", method="POST")
    @ApiResponse(responseCode = "201")
    @PostMapping("/apply")
    public ResponseEntity<Void> apply(@Valid @RequestBody RequestCounselingApply form, Errors errors) {

        // 추가 검증 - validator

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        // 서비스 추가

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "집단 상담(프로그램) 정보 하나 조회", method = "GET")
    @ApiResponse(responseCode = "200")
    @Parameter(name="pgmSeq", required = true, description = "경로변수, 집단 상담 정보 등록 번호")
    @GetMapping("/group/info/{pgmSeq}")
    public JSONData groupInfo(@PathVariable("pgmSeq") Long pgmSeq) {

        return null;
    }

    @Operation(summary = "집단 상담(프로그램) 정보 목록", method="GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/group")
    public JSONData groupList(@ModelAttribute GroupCounselingSearch search) {

        return null;
    }

    @Operation(summary = "집단 상담 신청", method = "POST")
    @ApiResponse(responseCode = "201")
    @Parameters({
            @Parameter(name="schdlSeq", required = true, description = "집단 상담 스케쥴 번호", example = "1111"),
            @Parameter(name="studentNo", required = true, description = "집단 상담 신청자의 학번", example = "20150411"),
            @Parameter(name="username", required = true, description = "집단 상담 신청자의 이름", example = "홍길동"),
            @Parameter(name="grade", required = true, description = "집단 상담 신청자의 학년", example = "1"),
            @Parameter(name="department", required = true, description = "집단 상담 신청자의 학과", example = "치킨학과")
    })
    @PostMapping("/group/apply")
    public ResponseEntity<JSONData> groupApply(@Valid @RequestBody RequestGroupCounselingApply form, Errors errors) {

        // 추가 검증 - validator
        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        // 서비스 연동
        groupCounselingApplyService.apply(form);

        HttpStatus status = HttpStatus.CREATED;
        JSONData jsonData = new JSONData(form);

        return ResponseEntity.status(status).body(jsonData);
    }

    @Operation(summary = "편성된 상담 일정 목록", method="GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/cs/list")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData csList(@ModelAttribute CounselingSearch search) {

        return null;
    }


    // 편성된 상담 변경 처리  PATCH /cs/change
    @Operation(summary="편성된 상담 변경 처리", method="PATCH")
    @ApiResponse(responseCode = "200")
    @PatchMapping("/cs/change")
    public void csChange() {
    }



    // 편성된 그룹 상담 변경 처리(참석 여부, 일지) - PATCH /cs/group/change
    @Operation(summary = "편성된 그룹 상담 변경 처리",  description = "참석 여부 업데이트, 일지 작성", method = "PATCH")
    @ApiResponse(responseCode = "200")
    @PatchMapping("/cs/group/change")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public void csGroupChange() {

    }

    @Operation(summary = "상담사 평점 - 개인 상담, 집단 상담")
    @GetMapping("/rating")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData getRating() {

        return null;
    }
}
