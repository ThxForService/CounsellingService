package com.thxforservice.reservation.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class GroupReserProgram {
    @Id
    @GeneratedValue
    private Long pgmRegSeq; //신청 일련 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PGM_SEQ")
    private GroupProgram program;

    private Boolean approval; //승인 여부
    private Boolean attend; //참석 여부

    private Long studentNo; //학번
    private String username;
    private String grade;
    private String department;
}
