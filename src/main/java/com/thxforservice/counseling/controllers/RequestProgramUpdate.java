package com.thxforservice.counseling.controllers;

import lombok.Data;

@Data
public class RequestProgramUpdate {
    private Boolean attended; //참석 여부
    private String log; //상담 일지
}
