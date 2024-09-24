package com.thxforservice.counseling.controllers;

import com.thxforservice.counseling.entities.GroupCounseling;
import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.services.GroupCounselingApplyService;
import com.thxforservice.counseling.services.GroupCounselingCancelService;
import com.thxforservice.counseling.services.GroupCounselingInfoService;
import com.thxforservice.counseling.services.GroupCounselingStatusService;
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


@Tag(name = "GroupCounseling", description = "그룹 상담 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class CounselingGroupController {
    /**
     * ------사용자---------
     * 1. 집단 상담 프로그램 신청(예약) - POST program/apply
     * <p>
     * 2. 집단 상담 프로그램 조회(단일)(every)  - GET program/info/{pgmSeq}
     * 집단 상담 프로그램 조회(다중)(every)  - GET program/info
     * <p>
     * 3. 집단 상담 프로그램 취소(사용자) - DELETE program/cancel/{pgmRegSeq}
     * <p>
     * 4. 집단 상담 예약 조회(사용자)(다중) - GET program/res/info
     * <p>
     * -------상담사 -------
     * - 편성된 프로그램의 신청내역 다중조회(목록)  - GET /cs/group/list
     * - 편성된 프로그램의 신청내역 단일조회 - GET /cs/group/info/{pgmSeq}
     * - 편성된 프로그램의 변경 처리(참석 여부, 일지) - PATCH /cs/group/change
     * - 참여율은 자동 계산
     * ----------------------
     */
    private final GroupCounselingInfoService groupCounselingInfoService;
    private final MemberUtil memberUtil;
    private final GroupCounselingStatusService counselingStatusService;
    private final GroupCounselingApplyService groupCounselingApplyService;
    private final GroupCounselingCancelService groupCounselingCancelService;
    private final Utils utils;

    @Operation(summary = "집단 상담(프로그램) 정보 단일 조회", method = "GET")
    @ApiResponse(responseCode = "200")
    @Parameter(name = "pgmSeq", required = true, description = "경로변수, 집단 상담 정보 등록 번호")
    @GetMapping("/program/info/{pgmSeq}")
    public JSONData groupInfo(@PathVariable("pgmSeq") Long pgmSeq) {

        GroupProgram groupProgram = groupCounselingInfoService.getProgram(pgmSeq);

        return new JSONData(groupProgram);
    }

    @Operation(summary = "집단 상담(프로그램) 정보 목록", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/program/info")
    public JSONData groupList(@ModelAttribute GroupCounselingSearch search) {

        ListData<GroupProgram> listData = groupCounselingInfoService.getGroupProgramList(search);

        return new JSONData(listData);

    }

    @Operation(summary = "집단 상담 신청", method = "POST")
    @ApiResponse(responseCode = "201")
    @Parameters({
            @Parameter(name = "pgmSeq", required = true, description = "집단 상담 프로그램 번호", example = "1111"),
    })
    @PostMapping("/program/apply/{pgmSeq}")
    public ResponseEntity<JSONData> groupApply(@PathVariable("pgmSeq") Long pgmSeq, @Valid @RequestBody RequestGroupCounselingApply form, Errors errors) {

        // 추가 검증 - validator
        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }
        form.setPgmSeq(pgmSeq);

        // 서비스 연동
        groupCounselingApplyService.apply(form);

        HttpStatus status = HttpStatus.CREATED;
        JSONData jsonData = new JSONData(form);

        return ResponseEntity.status(status).body(jsonData);
    }

    //집단 상담 프로그램 취소(사용자)
    @Operation(summary = "집단 상담 프로그램 취소(사용자)", method = "DELETE")
    @ApiResponse(responseCode = "200")
    @Parameters({
            @Parameter(name = "pgmSeq", required = true, description = "집단 상담 프로그램 번호", example = "1111"),
    })
    @DeleteMapping("program/cancel/{pgmSeq}")
    public JSONData groupDelete(@PathVariable("pgmSeq") Long pgmSeq) {

        GroupCounseling groupCounseling = groupCounselingCancelService.cancel(pgmSeq);

        return new JSONData(groupCounseling);
    }

    //집단 상담 예약 조회(사용자)(다중)
    @Operation(summary = "집단 상담 예약 조회 목록 (사용자)", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("program/res/info")
    public JSONData groupApplyList(@ModelAttribute GroupCounselingSearch search) {

        Member member = memberUtil.getMember();
        search.setEmail(List.of(member.getEmail()));
        ListData<GroupCounseling> CounselinglistData = groupCounselingInfoService.getGroupCounselingList(search);

        return new JSONData(CounselinglistData);
    }

    /* 집단 상담의 상담사 S */
    @Operation(summary = "편성된 프로그램의 신청내역 목록(프로그램번호를 경로변수로 받아서 조회)", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/cs/group/list/{pgmSeq}")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData csList(@PathVariable("pgmSeq") Long pgmSeq, @ModelAttribute GroupCounselingSearch search) {

        ListData<GroupCounseling> listData = groupCounselingInfoService.getCounselorGroupList(search);

        return new JSONData(listData);
    }

    // 편성된 프로그램의 신청내역 단일 조회 - GET /cs/group/info/{schdlSeq}
    @Operation(summary = "편성된 프로그램의 신청내역 단일 조회(신청번호로)", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/cs/group/info/{pgmRegSeq}")
    public JSONData csListOne(@PathVariable("pgmRegSeq") Long pgmRegSeq) {

        GroupCounseling counseling = groupCounselingInfoService.getGroupCounselingById(pgmRegSeq);

        return new JSONData(counseling);
    }

    // 편성된 프로그램의 변경 처리(참석 여부, 일지) - PATCH /cs/group/change
    @Operation(summary = "편성된 프로그램의 변경 처리", description = "참석 여부 업데이트, 일지 작성", method = "PATCH")
    @ApiResponse(responseCode = "200")
    @PatchMapping("/cs/group/change/{pgmRegSeq}")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public void csGroupChange(@PathVariable("pgmRegSeq") Long pgmRegSeq, @ModelAttribute RequestProgramUpdate programUpdate) {

        counselingStatusService.updateAttendAndLog(pgmRegSeq, programUpdate);

    }
    /* 집단 상담의 상담사 E */

    @Operation(summary = "상담사 평점 - 집단 상담")
    @GetMapping("/group/rating/")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData getRating() {

        return null;

    }
}
