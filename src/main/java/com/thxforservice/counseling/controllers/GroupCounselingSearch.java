package com.thxforservice.counseling.controllers;

import com.thxforservice.global.CommonSearch;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class GroupCounselingSearch extends CommonSearch {
    private LocalDateTime programStartDate; // 프로그램 수행일자
    private List<Long> pgmSeq; // 그룹 프로그램 ID

    private List<Long> pgmRegSeq; // 신청 번호 목록
}
