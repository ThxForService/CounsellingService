package com.thxforservice.counseling.controllers;

import com.thxforservice.counseling.services.GroupCounselingInfoService;
import com.thxforservice.counseling.services.GroupCounselingSaveService;
import com.thxforservice.counseling.validators.GroupCounselingValidator;
import com.thxforservice.global.Utils;
import com.thxforservice.global.exceptions.BadRequestException;
import com.thxforservice.global.rests.JSONData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Tag(name="CounselingAdmin", description = "상담 관리자 API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CounselingAdminController {

    private final HttpServletRequest request;
    private final GroupCounselingSaveService groupCounselingSaveService;
    private final GroupCounselingValidator groupCounselingValidator;

    private final Utils utils;
    private final GroupCounselingInfoService groupCounselingInfoService;

    /**
     * 1. 집단 상담 프로그램 관리
     *     - 집단 상담 프로그램 추가, 수정, 삭제
     *         추가 - POST /group
     *         수정 - PATCH /group/update/{pgmSeq}
     *         삭제 - DELETE /group/{pgmSeq}
     *
     *
     *
     * 2. 개별 상담 신청 관리
     *    - 개별 상담 신청 목록 - /apply
     *    - 개별 상담  신청 정보 - /apply/{cSeq}
     *
     *
     */
    // 집단 상담 S
    @Operation(summary = "집단 상담 프로그램 추가", method = "POST")
    @PostMapping("/group")
    public ResponseEntity<Void> register(@ModelAttribute RequestGroupCounselingSave form, Errors errors) {

        groupCounselingValidator.validate(form, errors);

        if (errors.hasErrors()) {

            throw new BadRequestException(utils.getErrorMessages(errors));
        }
        groupCounselingSaveService.addProgram(form);

        return save();
    }

    @Operation(summary = "집단 상담 프로그램 수정", method = "PATCH")
    @PatchMapping("/group/update/{pgmSeq}")
    public ResponseEntity<Void> update(@PathVariable("pgmSeq") Long pgmSeq, @ModelAttribute RequestGroupCounselingSave form, Model model) {

        groupCounselingSaveService.updateProgram(pgmSeq, form);

         return save();
    }

    public ResponseEntity<Void> save() {

        HttpStatus status = request.getMethod().toUpperCase().equals("POST") ? HttpStatus.CREATED : HttpStatus.OK;

        return ResponseEntity.status(status).build();
    }

    @Operation(summary = "집단 상담 프로그램 삭제")
    @DeleteMapping("/group/{pgmSeq}")
    public void delete(@PathVariable("pgmSeq") Long pgmSeq) {

        groupCounselingInfoService.deleteProgram(pgmSeq);
    }
    // 집단 상담 E

    // 개별 상담 S
    @Operation(summary = "개별 상담 신청 목록")
    @GetMapping("/apply")
    public JSONData privateApplyList() {
        return null;
    }

    @Operation(summary = "개별 상담 신청 정보")
    @GetMapping("/apply/{cSeq}")
    public JSONData privateApplyInfo(@PathVariable("cSeq") Long cSeq) {
        return null;
    }
}