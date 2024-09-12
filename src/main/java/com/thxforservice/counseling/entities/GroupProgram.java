package com.thxforservice.counseling.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thxforservice.global.entities.BaseMemberEntity;
import com.thxforservice.counseling.constants.ProgramStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Lob
    private String Description; // 프로그램 설명

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime programStartDate; // 프로그램 수행일자

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate; // 신청 시작일자

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate; // 신청 종료일자

    private int capacity; // 신청 정원

    private int currentCount; // 현재 인원

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ProgramStatus status; // 접수상태


}
