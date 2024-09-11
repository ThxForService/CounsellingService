package com.thxforservice.counselling.entities;

import com.thxforservice.global.entities.BaseEntity;
import com.thxforservice.counselling.constants.CCase;
import com.thxforservice.counselling.constants.CReason;
import com.thxforservice.counselling.constants.Status;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(length = 65, nullable = true)
    private String cCaseDetail; // 기타 선택시 값 입력

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

    @Column(nullable = false, length = 65)
    @ColumnDefault("1") // 기본값 1 = true = 약관동의
    private Long agree;

    @Lob
    private String content; // 상담 내용
}