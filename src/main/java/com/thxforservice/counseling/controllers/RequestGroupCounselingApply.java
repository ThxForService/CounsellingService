package com.thxforservice.counseling.controllers;

import lombok.Data;

/**
 * 상담 신청 기본 - 그룹 상담
 *
 */
@Data
public class RequestGroupCounselingApply { // GroupProgram 과 연동
    private Long studentNo; // 학번
    private String username; // 이름
    private String grade; // 학년
    private String department; // 학과
    private String email;
    private String mobile;
}
