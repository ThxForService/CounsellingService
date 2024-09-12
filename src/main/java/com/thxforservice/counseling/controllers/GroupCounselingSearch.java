package com.thxforservice.counseling.controllers;

import com.thxforservice.global.CommonSearch;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GroupCounselingSearch extends CommonSearch {
    private LocalDate startDate; // 검색 시작일자
    private LocalDate endDate; // 검색 종료일자

    private List<Long> pgmSeq; // 그룹 프로그램 ID
}
