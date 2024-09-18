package com.thxforservice.counseling.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thxforservice.counseling.constants.CCase;
import com.thxforservice.counseling.constants.CReason;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 개인 상담
 *
 */
@Data
public class RequestCounselingApply extends RequestGroupCounselingApply{

    // 상담 번호 - 수정시 필요함, Validator, 개인상담땐 필요없음 집단커맨드에는 있어야함
    private Long cSeq;
    // 상담 번호 - 수정시 필요함, Validator, 개인상담땐 필요없음 집단커맨드에는 있어야함

    @NotBlank
    private CReason cReason; // 상담 경위

    @NotBlank
    private CCase cCase; // 상담 유형

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime rTime;

}
