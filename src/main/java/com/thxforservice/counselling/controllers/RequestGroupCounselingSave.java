package com.thxforservice.counselling.controllers;

import com.thxforservice.counselling.constants.ProgramStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestGroupCounselingSave {

    private String pgmNm; // 프로그램명
    private Long empNo; //담당 상담원 사번
    private String description; // 프로그램 설명
    private LocalDate startDate; // 신청 시작일자
    private LocalDate endDate; // 신청 종료일자
    private int capacity; //제한인원
    private ProgramStatus status; // 접수상태

}


