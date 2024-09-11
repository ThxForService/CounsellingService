package com.thxforservice.counselling.controllers;

import lombok.Data;

/**
 * 상담 신청 기본 - 그룹 상담
 *
 */
@Data
public class RequestGroupCounselingApply  {
    private Long schdlSeq; // 그룹 스케줄 번호

    private Long studentNo; // 학번
    private String username; // 이름
    private String grade; // 학년
    private String department; // 학과
    private String email;
    private String mobile;
}