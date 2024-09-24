package com.thxforservice.counseling.controllers;

import com.thxforservice.counseling.constants.Status;
import com.thxforservice.counseling.controllers.RequestGroupCounselingSave;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.exceptions.CounselingNotFoundException;
import com.thxforservice.counseling.services.CounselingInfoService;
import com.thxforservice.counseling.services.CounselingStatusService;
import com.thxforservice.counseling.services.GroupCounselingInfoService;
import com.thxforservice.counseling.services.GroupCounselingSaveService;
import com.thxforservice.counseling.validators.GroupCounselingValidator;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Utils;
import com.thxforservice.global.exceptions.BadRequestException;
import com.thxforservice.global.rests.JSONData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    private final HttpServletRequest request;
    private final GroupCounselingSaveService groupCounselingSaveService;
    private final GroupCounselingValidator groupCounselingValidator;
    private final GroupCounselingInfoService groupCounselingInfoService;

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
     *     * -------상담사 -------
     *      * 1. 개인 상담 목록 조회 ( 다중 )
     *      * 2. 개인 상담 목록 조회 ( 단일 )
     *      * 5. 개인 상담 일정 변경 (상담사)
     *      * - 편성된 상담은 empNo로 조회된 상담
     *      * - 편성된 상담 일정 목록  GET /cs/list ( 다중 )
     *      * - 편성된 상담 하나 조회  GET /cs/info/{cSeq} ( 단일 )
     *      * - 편성된 상담 변경 처리(상태)  POST /cs/status
     *      * - 편성된 상담 변경 처리(일정, 일지)  PATCH /cs/change
     *      * - rDate, rTime
     *     * --------------------
     */

    /* 집단 상담 S */
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
    /* 집단 상담 E */

    /* 개별 상담 S */
    @Operation(summary = "상담사 예약 조회", method = "GET")
    @GetMapping("/cs/list")
    @Parameter(name = "CounselingSearch", description = "상담 검색 조건", required = false)
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData csList(CounselingSearch search) {

        ListData<Counseling> data = counselingInfoService.getList(search);

        return new JSONData(data);
    }

    @Operation(summary = "상담사 예약 상세 조회", method = "GET")
    @ApiResponse(responseCode = "201")
    @Parameter(name = "cSeq", description = "상담 ID", required = true, example = "1")
    @GetMapping("/cs/info/{cSeq}")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData csInfo(@PathVariable("cSeq") Long cSeq) {

        Counseling counseling = counselingInfoService.get(cSeq);

        return new JSONData(counseling);
    }

    @Operation(summary = "상담사 예약 상태 변경", method = "POST")
    @Parameter(name = "RequestCsChange", description = "상담 상태 변경 요청 폼", required = true)
    @PostMapping("/cs/status")
    @PreAuthorize("hasAnyAuthority(('COUNSELOR'))")
    public void CsChangeStatus(@Valid @RequestBody RequestCsChange form, Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }
        counselingStatusService.change(form.getCSeq(), Status.valueOf(form.getStatus()));
    }


    /* 개별 상담 E */

}
