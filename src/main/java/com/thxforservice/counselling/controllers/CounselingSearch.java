package com.thxforservice.counselling.controllers;

import com.thxforservice.counselling.constants.Status;
import com.thxforservice.global.CommonSearch;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CounselingSearch extends CommonSearch {

    private String status; // 예약 상태
    private Long studentId; // 학생 학번
    private LocalDate startDate; // 검색 시작일자
    private LocalDate endDate; // 검색 종료일자

}