package com.thxforservice.counselling.controllers;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 개인 상담
 */
@Data
public class RequestCounselingApply extends RequestGroupCounselingApply {

    private Long agree;

    private String cCase;

    private String cCaseDetail;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate rDate;

    @JsonFormat(pattern="HH:mm")
    private LocalTime rTime;
}