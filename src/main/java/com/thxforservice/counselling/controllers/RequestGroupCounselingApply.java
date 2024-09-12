package com.thxforservice.counselling.controllers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 상담 신청 기본 - 그룹 상담
 *
 */
@Data
public class RequestGroupCounselingApply { // GroupProgram 과 연동
    @NotNull
    private Long schdlSeq; //그룹 스케쥴 번호

    @NotNull
    private Long studentNo; // 학번

    @NotBlank
    private String username; // 이름

    @NotBlank
    private String grade; // 학년

    @NotBlank
    private String department; // 학과
}
