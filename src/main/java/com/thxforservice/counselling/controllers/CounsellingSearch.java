package com.thxforservice.counselling.controllers;

import com.thxforservice.counselling.constants.Status;
import com.thxforservice.global.CommonSearch;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CounsellingSearch extends CommonSearch {

    private LocalDate sDate;
    private LocalDate eDate;

    private List<Long> studentId; // 회원번호 목록

    private String status; // 예약상태
}
