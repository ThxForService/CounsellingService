package com.thxforservice.member.constants;

public enum Authority {
    ALL,
    STUDENT,
    COUNSELOR,
    ADMIN,

    EMPLOYED, // 재직중
    LEAVE, // 휴직중
    RESIGN, // 퇴직

    UNDERGRADUATE, // 학부생
    POSTGRADUATE, // 대학원생
    GRADUATE // 졸업생
}