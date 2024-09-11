package com.thxforservice.counselling.controllers;

import com.thxforservice.counselling.entities.Counseling;
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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
     *              - 엔티티 두개 참고
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
     *      - 편성된 상담 변경 처리(일정, 일지)  PATCH /cs/change
     *
     *      - 상담사 : 상담사로 권한 제한
     *      - rDate, rTime
     *
     *  6. 그룹 상담 (상담사)
     *      - 편성된 그룹 상담 목록 - GET /cs/group/list
     *      - 편성된 그룹 상담 한 개 조회 - GET /cs/group/info/{schdlSeq}
     *      - 편성된 그룹 상담 변경 처리(참석 여부, 일지, 참여율) - PATCH /cs/group/change
     *          - 참여율은 자동 계산
     */
/* 강사님 코드 S */
    @Operation(summary = "개인 상담 신청", method = "POST")
    @ApiResponse(responseCode = "201")
    @PostMapping("/apply")
    public ResponseEntity<Void> apply(@Valid @RequestBody RequestGroupCounselingApply form, Errors errors){ // 커맨드객체라던가 서비스, 검증 등

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));

        }
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "집단 상담 정보 1개 조회", method = "GET")
    @ApiResponse(responseCode = "200")
    @Parameter(name = "pgmSeq",required = true, description = "경로변수, 집단 상담 정보 등록 번호")
    @GetMapping("/group/info/{pgmSeq}")
    public JSONData groupInfo(@PathVariable("pgmSeq") Long pgmSeq) {

        return null;
    }

    @Operation(summary = "집단 상담 정보 목록", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/group")
    public JSONData groupList(@ModelAttribute GroupCounselingSearch search){

        return null;
    }

    @Operation(summary = "집단 상담 신청", method = "POST")
    @ApiResponse(responseCode = "201")
    @Parameter()
    @PostMapping("/group/apply")
    public ResponseEntity<Void> groupApply(@Valid @RequestBody RequestGroupCounselingApply form, Errors errors) {
        // 추가 검증 - validator
        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }
        // 서비스 연동...

        return null;
    }


    @Operation(summary = "편성된 상담 일정 목록", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/cs/list")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData csList(@ModelAttribute CounselingSearch search) { // 파라미터 = 커맨드객체
        // empNo로 관리, 본인것만
        return null;
    }

    // 편성된 상담 하나 조회 GET /cs/info/{seq}
    @Operation(summary = "편성된 상담 한 개 조회", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/cs/info/{cSeq}")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData csInfo(@PathVariable("cSeq") Long seq) {

        return null;
    }

    // 편성된 상담 변경 처리 PATCH /cs/change
    @Operation(summary = "편성된 상담 변경 처리",method = "GET")
    @PatchMapping("/cs/change")
    public void csChange() {

    }

    //    - 편성된 그룹 상담 목록 - GET /cs/group/list
    @Operation(summary = "편성된 그룹 상담 목록", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/cs/group/list")
    public JSONData csGroupList() {

        return null;
    }

    //    - 편성된 그룹 상담 한 개 조회 - GET /cs/group/info/{schdlSeq}
    @Operation(summary = "편성된 그룹 상담 한 개 조회", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/cs/group/info/{schdlSeq}")
    public JSONData csGroupInfo(@PathVariable("schdlSeq") Long schdlSeq) {

        return null;
    }

    @Operation(summary = "편성된 그룹 상담 변경 처리", description = "참석 여부 업데이트, 일지 작성", method = "PATCH")
    @ApiResponse(responseCode = "200")
    @PatchMapping("/cs/group/change")
    public void CsGroupChange() {

    }

    @Operation(summary = "상담사 평점 - 개인 상담, 집단 상담")
    @GetMapping("/rating")
    @PreAuthorize("hasAnyAuthority('COUNSELOR')")
    public JSONData getRating() {

        return null;
    }

/* 강사님 코드 E */

//    private final CounsellingInfoService infoService;
//    private final MemberUtil memberUtil;
//    private final CounsellingValidator validator;
//    private final CounsellingApplyService applyService;
    private final Utils utils;
//
//    /**
//     * 예약접수
//     *
//     * @return
//     */
//    @PostMapping("/apply")
//    public ResponseEntity<JSONData> apply(@RequestBody @Valid RequestCounselingApply form, Errors errors) {
//
//        validator.validate(form, errors); // 예약 검증
//
//        if (errors.hasErrors()) {
//            throw new BadRequestException(utils.getErrorMessages(errors));
//        }
//
//        Counseling counseling = applyService.apply(form);
//
//        HttpStatus status = HttpStatus.CREATED;
//        JSONData jsonData = new JSONData(counseling);
//        jsonData.setStatus(status);
//
//        return ResponseEntity.status(status).body(jsonData);
//    }
//
//
//    /**
//     * 예약 정보 조회
//     */
//    @GetMapping("/complete/{seq}")
//    public JSONData rsvInfo(@PathVariable("seq") Long seq) {
//        Counseling counseling = infoService.get(seq);
//
//        return new JSONData(counseling);
//    }
//
//    @GetMapping("/list") //예약은 회원가입한 멤버만 예약 조회 가능 -> 회원이 아니면 404
//    public JSONData list(CounselingSearch search) {
//        Member member = memberUtil.getMember();
//        search.setStudentId(member.getSeq());
//
//        ListData<Counseling> data = infoService.getList(search);
//
//        return new JSONData(data);
//    }
}
