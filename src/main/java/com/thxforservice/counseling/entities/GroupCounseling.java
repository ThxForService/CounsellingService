package com.thxforservice.counseling.entities;

import com.thxforservice.global.entities.BaseMemberEntity;
import com.thxforservice.counseling.constants.ProgramStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    /** 그룹 상담 스케쥴 목록 */
    @Transient
    private List<GroupSchedule> schedules;

}
