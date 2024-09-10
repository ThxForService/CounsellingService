package com.thxforservice.counselling.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class GroupProgram {

    @Id
    @GeneratedValue
    private Long pgmRegSeq; // 신청 일련 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PGM_SEQ")
    private GroupCounselling program;

    private Boolean attend; // 참석 여부

    /* 새로운 요청이 아니라 로그인 정보를 가지고 출력 S*/
    private Long studentNo; // 학번
    private String username; // 이름
    private String grade; // 학년
    private String department; // 학과
    /* 새로운 요청이 아니라 로그인 정보를 가지고 출력 E*/


}
