package com.thxforservice.counselling.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thxforservice.counselling.constants.CCase;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestCounselling { // 커맨드 객체

    @NotBlank
    private CCase cCase; // 상담 유형

    @NotBlank
    private String gid; // 회기 구분

    @NotBlank
    private Long studentNo; // 예약자 학번

    @NotBlank
    @Email
    private String email; // 예약자 이메일

    @NotBlank
    private String mobile; //예약자 전화번혼

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rDate; // 예약일

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime rTime; // 예약시간

    @AssertTrue
    private boolean agree; // 약관동의 기본값 = true,
}
