package com.thxforservice.counseling.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thxforservice.global.entities.BaseEntity;
import com.thxforservice.counseling.constants.CCase;
import com.thxforservice.counseling.constants.CReason;
import com.thxforservice.counseling.constants.Status;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(length = 45, nullable = false)
    private String gid; // 회기

    @Column(nullable = false)
    private LocalDate rDate;

    @Column(nullable = false)
    private LocalTime rTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CReason cReason; //상담경위

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CCase cCase;  //상담유형

    @Column(nullable = false, length = 45)
    private String username; // 이름

    @Column(nullable = false, length = 65)
    private Long studentNo; // 학번

    @Column(nullable = false, length = 65)
    private String empNo; // 사번

    @Column(nullable = false, length = 65)
    private String mobile;

    @Column(length = 65)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status; //예약상태

    @Lob
    private String content; // 상담 내용
}

