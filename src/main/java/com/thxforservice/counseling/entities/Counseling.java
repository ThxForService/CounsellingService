package com.thxforservice.counseling.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thxforservice.counseling.constants.CCase;
import com.thxforservice.counseling.constants.CReason;
import com.thxforservice.counseling.constants.Status;
import com.thxforservice.global.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Counseling extends BaseEntity {

    @Id
    @GeneratedValue
    private Long cSeq;

    @Column(length = 45)
    private String gid; // 회기

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate rDate; // 예약 일자

    @Column(nullable = false)
    @JsonFormat(pattern="HH:mm")
    private LocalTime rTime; // 예약 시간

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CReason cReason; //상담경위

    @Enumerated(EnumType.STRING)
    private CCase cCase;  //상담유형

    private String customCase;

    @Column(nullable = false, length = 45)
    private String username; // 이름

    @Column(nullable = false, length = 65)
    private Long studentNo; // 학번

    @Column(length = 65)
    private String empNo; // 사번

    @Column(nullable = false, length = 65)
    private String mobile; // 전화번호

    @Column(length = 65)
    private String email; // 이메일

    @Enumerated(EnumType.STRING)
    @Column()
    private Status status; // 예약 상태

    @Lob
    private String content; // 상담 일지
}

