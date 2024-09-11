package com.thxforservice.counselling.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestGroupCounselingxxxxxx {
    private Long programId; // 그룹 프로그램 ID -> 예약하려는 집단 상담 프로그램을 식별하기 위해 필요

    private Long studentNo; // 학번

    private String username; //학생명

    private String grade; // 학년

    private String department; // 학과

    private Boolean attend; // 참석 여부

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDate;

    @Min(value = 5, message = "5명이상 신청 시에만 상담이 진행됩니다.")
    @Max(value = 30, message = "상담 인원은 30명을 초과할 수 없습니다.")
    private int capacity; //정원

}
