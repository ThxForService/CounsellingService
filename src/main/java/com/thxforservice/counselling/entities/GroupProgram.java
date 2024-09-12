package com.thxforservice.counselling.entities;

import com.thxforservice.counselling.constants.ProgramStatus;
import com.thxforservice.global.entities.BaseMemberEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupProgram extends BaseMemberEntity { //상담 프로그램 정보
    @Id @GeneratedValue
    private Long pgmSeq;

    @Column(length = 100, nullable = false)
    private String pgmNm; // 프로그램명

    @Column(nullable = false)
    private Long empNo;

    @Lob
    private String Description; // 프로그램 설명

    private LocalDate date; // 진행일자

    private LocalDate startDate; // 신청 시작일자

    private LocalDate endDate; // 신청 종료일자

    private int capacity; // 제한 정원

    private int currentCount; //신청 인원

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ProgramStatus status; // 접수상태

    @Lob
    private String memo; // 상담일지

    private Double rate; // 참여율


}
