package com.thxforservice.reservation.entities;

import com.thxforservice.global.entities.BaseEntity;
import com.thxforservice.reservation.constants.CCase;
import com.thxforservice.reservation.constants.CReason;
import com.thxforservice.reservation.constants.Status;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //GenerationType.IDENTITY-> 시퀀스 번호가 1부터 들어갈 수 있도록 함 but 보안에 취약하다는 문제가 있음
    // 소규모 프로젝트이기 때문에 일단 보안보다는 보여지는 것이 중요하다고 판단
    private Long cSeq;

    @Column(nullable = false)
    private LocalDate rDate;

    @Column(nullable = false)
    private LocalDateTime rTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CReason cReason; //상담경위

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CCase cCase;  //상담유형

    @Column(length = 65)
    private String cCaseDetail; //기타상담유형

    @Column(nullable = false)
    private String memberID;

    @Column(nullable = false, length = 65)
    private String studentNo;

    @Column(nullable = false, length = 65)
    private String empNo;

    @Column(nullable = false, length = 65)
    private String mobile;

    @Column(length = 65)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status; //예약상태

    @Column(nullable = false, length = 65)
    private String agree;
}

