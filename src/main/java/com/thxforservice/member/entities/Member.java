package com.thxforservice.member.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thxforservice.member.constants.Authority;
import com.thxforservice.member.constants.Status;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {

    private Long memberSeq; // 멤버 기본 키

    private String gid;

    private String email;

    private String password;

    private String userName;

    private String mobile;

    private Authority authority; // 권한

    /* 학생 정보 S */
    private Long studentNo; // 학번
    private String grade; // 학년
    private String department; // 학과
    /* 학생 정보 E */

    /* 상담사 정보 S */
    private Long empNo; // 사번
    private Status status; // 휴직재직퇴직
    private Double rating; // 별점
    /* 상담사 정보 E */
}