package com.thxforservice.counseling.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thxforservice.counseling.constants.CCase;
import com.thxforservice.counseling.constants.CReason;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 개인 상담
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestCounselingApply{

    @NotNull
    private Long studentNo;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String mobile;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime rTime;

    private CReason cReason;

    private CCase cCase;

    private String customCase;
}
