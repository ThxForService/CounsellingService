package com.thxforservice.reservation.controllers;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class RequestGroupReservation {
    private Long programId; // 그룹 프로그램 ID -> 예약하려는 집단 상담 프로그램을 식별하기 위해 필요

    private Long studentNo; // 학번

    private String username; //학생명

    private String grade; // 학년

    private String department; // 학과

    private Boolean approval; // 승인 여부

    private Boolean attend; // 참석 여부

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDate;

}
