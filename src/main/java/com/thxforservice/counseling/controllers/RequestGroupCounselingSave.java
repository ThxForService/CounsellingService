package com.thxforservice.counseling.controllers;

import com.thxforservice.counseling.constants.ProgramStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestGroupCounselingSave {

    private String pgmNm; // 프로그램명
    private String description; // 프로그램 설명
    private LocalDate programStartDate;  //프로그램 수행 날자.
    private LocalDate startDate; // 신청 시작일자
    private LocalDate endDate; // 신청 종료일자
    private Integer capacity; // 신청 정원
    private ProgramStatus status; // 접수상태

}


