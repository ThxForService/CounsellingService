package com.thxforservice.counseling.controllers;

import com.thxforservice.counseling.constants.Status;
import com.thxforservice.global.CommonSearch;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CounselingSearch extends CommonSearch {

    private List<Long> cSeq; // 상담번호
    private String status; // 예약 상태
    private List<Long> studentNo; // 학생 학번
    private LocalDate startDate; // 검색 시작일자
    private LocalDate endDate; // 검색 종료일자
    private List<String> empNo; // 사번
    private String content; // 상담 내용

}
