package com.thxforservice.reservation.entities;

import com.thxforservice.global.entities.BaseMemberEntity;
import com.thxforservice.reservation.constants.ProgramStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class GroupProgram extends BaseMemberEntity {
    @Id
    @GeneratedValue
    private Long pgmSeq;

    @Column(length = 100, nullable = false)
    private String pgmNm; //프로그램명

    @Lob
    private String description; //프로그램 설명
    private LocalDate startDate; //신청 시작 일자
    private LocalDate endDate; //신청 종료 일자

    private int capacity; // 신청 정원

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ProgramStatus status;
}
