package com.thxforservice.member.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thxforservice.member.constants.Authority;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import com.thxforservice.member.constants.Authority;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {

    /**
     * 진짜 엔티티가 아니라 데이터 담는 거니깐 Member에
     * 모든 직업 다 담아도 됌
     *
     */

    private Long memberSeq;

    private String gid;

    private String email;

    private String password;

    private String userName;

    private String mobile;

    private Authority authority;
}