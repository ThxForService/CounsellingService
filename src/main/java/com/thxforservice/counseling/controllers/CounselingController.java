package com.thxforservice.counseling.controllers;

import com.thxforservice.counseling.entities.Counseling;
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
     * 1. 개인 상담 신청  - POST /apply | Gooooood
     * - 개인 상담 목록 조회 ( 다중 ) | Goooood
     * - 개인 상담 목록 조회 ( 단일 ) | Gooood
     * - 개인 상담 예약 취소 | Goood
     * ----------------------
     */

    private final Utils utils;
    private final CounselingValidator validate;
    private final CounselingApplyService applyService;
    private final CounselingInfoService infoService;
    private final MemberUtil memberUtil;
    private final CounselingStatusService statusService;
    private final CounselingCancelService cancelService;

    @Operation(summary = "개인 상담 신청", description = "개인 상담 신청을 처리합니다.", method = "POST")
    @ApiResponse(responseCode = "201", description = "개인 상담 신청 성공")
    @Parameter(name = "RequestCounselingApply", description = "상담 신청 양식", required = true)
    @PostMapping("/apply")
    public ResponseEntity<Void> apply(@Valid @RequestBody RequestCounselingApply form, Errors errors) {

        // 추가 검증 - validator
        validate.validate(form, errors);

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        // 서비스 추가
        // Counseling 객체를 생성하고 데이터에 저장
        Counseling counseling = applyService.apply(form);

        // 예약 완료 후 상담사 배정
        applyService.applyRandom(counseling.getCSeq());

        // HTTP 응답 상태를 생성 - 상담 신청이 성공했음을 나타내기 위해 201(Created) 상태 코드를 설정
        HttpStatus status = HttpStatus.CREATED;

        // JSON 응답 생성 - counseling 데이터를 JSON으로 변환하여 응답 본문에 포함
        JSONData jsonData = new JSONData(counseling);

        // 응답 상태를 설정 (201 Created 상태 코드로 설정)
        jsonData.setStatus(status);

        // HTTP 응답을 반환 - 201 Created 상태 코드와 함께 응답 본문을 클라이언트에게 반환
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "개인 상담 목록 조회", description = "로그인한 사용자의 개인 상담 목록을 조회합니다.", method = "GET")
    @Parameter(name = "CounselingSearch", description = "검색 조건", required = false)
    @ApiResponse(responseCode = "200", description = "개인 상담 목록 조회 성공")
    @GetMapping("/list")
    public JSONData List(CounselingSearch search) {

        Member member = memberUtil.getMember();
        search.setStudentNo(List.of(member.getMemberSeq()));

        ListData<Counseling> listData = infoService.getList(search);

        return new JSONData(listData);

    }

    @Operation(summary = "개인 상담 상세 조회", description = "개인 상담의 상세 정보를 조회합니다.", method = "GET")
    @ApiResponse(responseCode = "200", description = "개인 상담 상세 조회 성공")
    @Parameter(name = "cSeq", description = "상담 ID", required = true, example = "1")
    @GetMapping("/list/{cSeq}")
    public JSONData info(@PathVariable("cSeq") Long cSeq) {

        Counseling counseling = infoService.get(cSeq, true);

        return new JSONData(counseling);
    }

    @Operation(summary = "개인 상담 예약 취소", description = "개인 상담 예약을 취소합니다.", method = "PATCH")
    @ApiResponse(responseCode = "200", description = "개인 상담 예약 취소 성공")
    @PatchMapping("/cancel/{cSeq}")
    public JSONData cancel(@PathVariable("cSeq") Long cSeq) {
        Counseling item = cancelService.cancel(cSeq);

        return new JSONData(item);
    }

    @Operation(summary = "상담사 평점 조회", description = "상담사의 평점을 조회합니다.", method = "GET")
    @ApiResponse(responseCode = "200", description = "상담사 평점 조회 성공")
    @Parameter(name = "cSeq", description = "상담 ID", required = true, example = "1")
    @GetMapping("/rating")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData getRating() {

        return null;
    }
}
