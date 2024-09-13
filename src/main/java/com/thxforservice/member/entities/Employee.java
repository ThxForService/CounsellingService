package com.thxforservice.member.entities;


import com.thxforservice.member.constants.Status;
import lombok.Data;

@Data
public class Employee extends Member{

    private Long empNo;

    private Status status;

    private Double rating;
}
