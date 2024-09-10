package com.thxforservice.counselling.entities;

import com.thxforservice.global.entities.BaseMemberEntity;
import com.thxforservice.counselling.constants.ProgramStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Data
@Entity
public class GroupCounseling extends BaseMemberEntity {
    @Id @GeneratedValue
    private Long pgmSeq;

    @Column(length = 100, nullable = false)
    private String pgmNm; // 프로그램명

    @Lob
    private String Description; // 프로그램 설명

    private LocalDate startDate; // 신청 시작일자

    private LocalDate endDate; // 신청 종료일자

    @ColumnDefault("1")
    private int capacity; // 신청 정원

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ProgramStatus status; // 접수상태


}
