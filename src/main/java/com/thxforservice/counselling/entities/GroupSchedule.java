package com.thxforservice.counselling.entities;

import com.thxforservice.global.entities.BaseMemberEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class GroupSchedule extends BaseMemberEntity {
    // 프로그램 일정으로 신청을 해야함
    @Id @GeneratedValue
    private Long schdlSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PGM_SEQ")
    private GroupCounseling program; // 프로그램번호

    private LocalDate date; // 진행일자

    @Lob
    private String memo; // 상담일지

    private Double rate; // 참여율

    @Transient
    private List<GroupProgram> students;
}