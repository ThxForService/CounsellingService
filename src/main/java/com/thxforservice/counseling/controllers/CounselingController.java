package com.thxforservice.counseling.controllers;

import com.thxforservice.counseling.constants.Status;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.repositories.CounselingRepository;
import com.thxforservice.counseling.services.*;
import com.thxforservice.counseling.validators.CounselingValidator;
import com.thxforservice.global.ListData;
import com.thxforservice.global.Utils;
import com.thxforservice.global.exceptions.BadRequestException;
import com.thxforservice.global.rests.JSONData;
import com.thxforservice.member.MemberUtil;
import com.thxforservice.member.entities.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Counseling", description = "개인 상담 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/counseling")
public class CounselingController {
    /**
     * ------사용자---------
     * 1. 개인 상담 신청  - POST /apply
     * - 개인 상담 목록 조회 ( 다중 )
     * - 개인 상담 목록 조회 ( 단일 )
     * -------상담사 -------
     * 1. 개인 상담 목록 조회 ( 다중 )
     * 2. 개인 상담 목록 조회 ( 단일 )
     * 5. 개인 상담 일정 변경 (상담사)
     * - 편성된 상담은 empNo로 조회된 상담
     * - 편성된 상담 일정 목록  GET /cs/list
     * - 편성된 상담 하나 조회  GET /cs/info/{cSeq}
     * - 편성된 상담 변경 처리(일정, 일지)  PATCH /cs/change
     * - rDate, rTime
     * ----------------------
     */

    private final Utils utils;
    private final CounselingValidator validate;
    private final CounselingApplyService applyService;
    private final CounselingInfoService infoService;
    private final MemberUtil memberUtil;
    private final CounselingStatusService statusService;

    @Operation(summary = "개인 상담 신청", method = "POST")
    @ApiResponse(responseCode = "201")
    @PostMapping("/apply")
    public ResponseEntity<Void> apply(@Valid @RequestBody RequestCounselingApply form, Errors errors) {

        // 추가 검증 - validator
        validate.validate(form, errors);

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        // 서비스 추가
        Counseling counseling = applyService.apply(form);

        HttpStatus status = HttpStatus.CREATED;
        JSONData jsonData = new JSONData(counseling);
        jsonData.setStatus(status);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "개인 상담 예약 조회", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/list")
    public JSONData List(CounselingSearch search) {

        Member member = memberUtil.getMember();
        search.setStudentNo(List.of(member.getSeq()));

        ListData<Counseling> listData = infoService.getList(search);

        return new JSONData(listData);

    }

    @Operation(summary = "개인 상담 예약 상세 조회", method = "GET")
    @ApiResponse(responseCode = "201")
    @GetMapping("/cs/info")
    public JSONData info(@PathVariable("cSeq") Long cSeq) {

        Counseling counseling = infoService.get(cSeq, true);

        return new JSONData(counseling);
    }

    @Operation(summary = "개인 상담 예약 취소", method = "POST")
    @ApiResponse(responseCode = "201")
    @PostMapping("/cancel/{cSeq}")
    public JSONData cancel (@PathVariable("cSeq") Long cSeq) {
       Counseling item = infoService.cancel(cSeq);

        return new JSONData(item);
    }

    @Operation(summary = "상담사의 학생 예약 조회", method = "GET")
    @GetMapping("/cs/list")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData csList(CounselingSearch search) {

        ListData<Counseling> data = infoService.getList(search);

        return new JSONData(data);
    }

    @Operation(summary = "상담사의 학생 예약 상세 조회", method = "GET")
    @ApiResponse(responseCode = "201")
    @GetMapping("/cs/info")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData csInfo(@PathVariable("cSeq") Long cSeq) {

        Counseling counseling = infoService.get(cSeq);

        return new JSONData(counseling);
    }

    @Operation(summary = "상담사의 학생 예약 상태 변경", method = "POST")
    @PostMapping("/cs/status")
    @PreAuthorize("hasAnyAuthority(('COUNSELOR'))")
    public void CsChangeStatus(@Valid @RequestBody RequestCsChange form, Errors errors){
        if(errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }
        statusService.change(form.getCSeq(), Status.valueOf(form.getStatus()));
    }

    @Operation(summary = "상담사 평점 - 개인 상담, 집단 상담")
    @GetMapping("/rating")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData getRating() {

        return null;
    }
}
