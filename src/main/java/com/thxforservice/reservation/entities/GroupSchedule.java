package com.thxforservice.reservation.entities;

import com.thxforservice.global.entities.BaseMemberEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class GroupSchedule extends BaseMemberEntity {
    @Id
    @GeneratedValue
    private Long schdlSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PGM_SEQ")
    private GroupProgram program;

    private LocalDate date;

    @Lob
    private String memo;

    private Double rate; //참여율
}
