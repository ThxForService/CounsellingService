package com.thxforservice.counseling.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thxforservice.counseling.constants.Status;
import com.thxforservice.global.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class GroupCounseling extends BaseEntity { // 신청 결과

    @Id
    @GeneratedValue
    private Long pgmRegSeq; // 신청 일련 번호

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name="pgmSeq")
    private GroupProgram program;

    private Boolean attend; // 참석 여부

    /* 새로운 요청이 아니라 로그인 정보를 가지고 출력 S*/
    private Long studentNo; // 학번
    private String username; // 이름
    private String grade; // 학년
    private String department; // 학과
    private String email;
    private String mobile;
    /* 새로운 요청이 아니라 로그인 정보를 가지고 출력 E*/

    private String counselorLog; // 상담 일지

    private Integer rating;  // 별점 (1-5 사이의 값)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status; // 예약 상태

    @Transient
    private String pgmNm;

    @Transient
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime pgmStartDate;

}
