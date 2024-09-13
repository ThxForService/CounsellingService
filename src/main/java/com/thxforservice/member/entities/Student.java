package com.thxforservice.member.entities;

import lombok.Data;

@Data
public class Student extends Member {

    private Long studentNo;


    private String grade; // 학년


    private String department; // 학과

}
