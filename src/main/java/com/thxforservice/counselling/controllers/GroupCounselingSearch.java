package com.thxforservice.counselling.controllers;

import com.thxforservice.global.CommonSearch;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GroupCounselingSearch extends CommonSearch {
    private LocalDate startDate; // 검색 시작일자
    private LocalDate endDate; // 검색 종료일자

    private String programName; // 프로그램명
    private String description; // 프로그램 설명
    private String memo; // 상담일지 내용

    private Long programId; // 그룹 프로그램 ID
    private Long scheduleId; // 일정 ID
}