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
public class RequestCounselingApply extends RequestGroupCounselingApply{

    // 상담 번호 - 수정시 필요함, Validator, 개인상담땐 필요없음 집단커맨드에는 있어야함
    private Long cSeq;

    @NotNull
    private CReason cReason; // 상담 경위

    private CCase cCase; // 상담 유형

    private String customCase; // 기타 선택시 값 입력

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime rTime;

}
