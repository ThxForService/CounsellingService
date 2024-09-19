package com.thxforservice.counseling.controllers;

import com.thxforservice.counseling.constants.Status;
import com.thxforservice.counseling.controllers.RequestGroupCounselingSave;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.exceptions.CounselingNotFoundException;
import com.thxforservice.counseling.services.CounselingInfoService;
import com.thxforservice.counseling.services.CounselingStatusService;
import com.thxforservice.counseling.services.GroupCounselingDeleteService;
import com.thxforservice.counseling.services.GroupCounselingSaveService;
import com.thxforservice.counseling.validators.GroupCounselingValidator;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Utils;
import com.thxforservice.global.exceptions.BadRequestException;
import com.thxforservice.global.rests.JSONData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name="CounselingAdmin", description = "상담 관리자 API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CounselingAdminController {

    private final GroupCounselingDeleteService deleteService;
    private final HttpServletRequest request;
    private final GroupCounselingSaveService counselingSaveService;
    private final GroupCounselingValidator validator;
    private final CounselingStatusService counselingStatusService;
    private final CounselingInfoService counselingInfoService;

    private final Utils utils;

    /**
     * 1. 집단 상담 프로그램 관리
     *     - 집단 상담 프로그램 추가, 수정, 삭제
     *         추가 - POST /group
     *         수정 - PATCH /group/update/{pgmSeq}
     *         삭제 - DELETE /group/{pgmSeq}
     *
     * 2. 개별 상담 신청 관리
     *    - 개별 상담 신청 목록 - /apply
     *    - 개별 상담  신청 정보 - /apply/{cSeq}
     *
     */
    // 집단 상담 S
    @Operation(summary = "집단 상담 프로그램 추가", method = "POST")
    @PostMapping("/group")
    public ResponseEntity<Void> register(@ModelAttribute RequestGroupCounselingSave form, Errors errors) {

        validator.validate(form, errors);

        if (errors.hasErrors()) {

            throw new BadRequestException(utils.getErrorMessages(errors));
        }
        counselingSaveService.addProgram(form);

        return save();
    }

    @Operation(summary = "집단 상담 프로그램 수정", method = "PATCH")
    @PatchMapping("/group/update/{pgmSeq}")
    public ResponseEntity<Void> update(@PathVariable("pgmSeq") Long pgmSeq, @ModelAttribute RequestGroupCounselingSave form, Model model) {

         counselingSaveService.updateProgram(pgmSeq, form);

         return save();
    }

    public ResponseEntity<Void> save() {

        HttpStatus status = request.getMethod().toUpperCase().equals("POST") ? HttpStatus.CREATED : HttpStatus.OK;

        return ResponseEntity.status(status).build();
    }

    @Operation(summary = "집단 상담 프로그램 삭제")
    @DeleteMapping("/group/{pgmSeq}")
    public void delete(@PathVariable("pgmSeq") Long pgmSeq) {

        deleteService.deleteProgram(pgmSeq);
    }
    // 집단 상담 E

    // 개별 상담 S
    @Operation(summary = "상담사의 학생 예약 조회", method = "GET")
    @GetMapping("/cs/list")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData csList(CounselingSearch search) {

        ListData<Counseling> data = counselingInfoService.getList(search);

        return new JSONData(data);
    }

    @Operation(summary = "상담사의 학생 예약 상세 조회", method = "GET")
    @ApiResponse(responseCode = "201")
    @GetMapping("/cs/info")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData csInfo(@PathVariable("cSeq") Long cSeq) {

        Counseling counseling = counselingInfoService.get(cSeq);

        return new JSONData(counseling);
    }

    @Operation(summary = "상담사의 학생 예약 상태 변경", method = "POST")
    @PostMapping("/cs/status")
    @PreAuthorize("hasAnyAuthority(('COUNSELOR'))")
    public void CsChangeStatus(@Valid @RequestBody RequestCsChange form, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }
        counselingStatusService.change(form.getCSeq(), Status.valueOf(form.getStatus()));
    }
    // 개별 상담 E

}