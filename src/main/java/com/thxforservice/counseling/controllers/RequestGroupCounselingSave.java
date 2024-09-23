package com.thxforservice.counseling.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thxforservice.counseling.constants.ProgramStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestGroupCounselingSave {

    private String pgmNm; // 프로그램명
    private String description; // 프로그램 설명
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate programStartDate;  //프로그램 수행 날자.

    @JsonFormat(pattern="HH:mm")
    private LocalTime programStartTime; // 프로그램 수행 시간

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate; // 신청 시작일자

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate; // 신청 종료일자

    @NotNull
    private Integer capacity; // 신청 정원
    private ProgramStatus status; // 접수상태

    @NotNull
    private Long empNo;

}


