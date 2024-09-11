package com.thxforservice.counselling.controllers;

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
    private final Utils utils;
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
     *  5. 개인 상담 일정 변경 (상담사)
     *      - 편성된 상담은 empNo로 조회된 상담
     *      - 편성된 상담 일정 목록  GET /cs/list
     *      - 편성된 상담 하나 조회  GET /cs/info/{cSeq}
     *      - 편성된 상담 변경 처리(일정, 일지)  PATCH /cs/change
     *
     *      - 상담사 : 상담사로 권한 제한
     *      - rDate, rTime
     *
     * 6. 집단 상담 (상담사)
     *      - 편성된 그룹 상담 목록  - GET /cs/group/list
     *      - 편성된 그룹 상담 하나 조회 - GET /cs/group/info/{schdlSeq}
     *      - 편성된 그룹 상담 변경 처리(참석 여부, 일지) - PATCH /cs/group/change
     *          - 참여율은 자동 계산
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

    @Operation(summary = "집단 상담 정보 하나 조회", method = "GET")
    @ApiResponse(responseCode = "200")
    @Parameter(name="pgmSeq", required = true, description = "경로변수, 집단 상담 정보 등록 번호")
    @GetMapping("/group/info/{pgmSeq}")
    public JSONData groupInfo(@PathVariable("pgmSeq") Long pgmSeq) {

        return null;
    }

    @Operation(summary = "집단 상담 정보 목록", method="GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/group")
    public JSONData groupList(@ModelAttribute GroupCounselingSearch search) {

        return null;
    }

    @Operation(summary = "집단 상담 신청", method = "POST")
    @ApiResponse(responseCode = "201")
    @Parameters({

    })
    @PostMapping("/group/apply")
    public ResponseEntity<Void> groupApply(@Valid @RequestBody RequestGroupCounselingApply form, Errors errors) {

        // 추가 검증 - validator
        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        // 서비스 연동 ..

        return null;
    }

    @Operation(summary = "편성된 상담 일정 목록", method="GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/cs/list")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData csList(@ModelAttribute CounselingSearch search) {

        return null;
    }

    @Operation(summary = "편성된 상담 하나 조회", method="GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/cs/info/{cSeq}")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData csInfo(@PathVariable("cSeq") Long cSeq) {

        return null;
    }

    // 편성된 상담 변경 처리  PATCH /cs/change

    @Operation(summary="편성된 상담 변경 처리", method="PATCH")
    @ApiResponse(responseCode = "200")
    @PatchMapping("/cs/change")
    public void csChange() {

    }

    // 편성된 그룹 상담 목록  - GET /cs/group/list
    @Operation(summary="편성된 그룹 상담 목록", method="GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/cs/group/list")
    public JSONData csGroupList() {

        return null;
    }

    // 편성된 그룹 상담 하나 조회 - GET /cs/group/info/{schdlSeq}
    @Operation(summary = "편성된 그룹 상담 하나 조회", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/cs/group/info/{schdlSeq}")
    public JSONData csGroupInfo(@PathVariable("schdlSeq") Long schdlSeq) {

        return null;
    }
    // 편성된 그룹 상담 변경 처리(참석 여부, 일지) - PATCH /cs/group/change
    @Operation(summary = "편성된 그룹 상담 변경 처리",  description = "참석 여부 업데이트, 일지 작성", method = "PATCH")
    @ApiResponse(responseCode = "200")
    @PatchMapping("/cs/group/change")
    public void csGroupChange() {

    }

    @Operation(summary = "상담사 평점 - 개인 상담, 집단 상담")
    @GetMapping("/rating")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData getRating() {

        return null;
    }
}