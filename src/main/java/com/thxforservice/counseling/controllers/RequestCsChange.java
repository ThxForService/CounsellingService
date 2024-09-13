package com.thxforservice.counseling.controllers;

import com.thxforservice.member.constants.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RequestCsChange {

    @NotNull
    private List<Long> cSeq; // 예약번호

    @NotNull
    private String status; // 예약 상태
}
