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

    private Long seq;

    private String gid;

    private String email;

    private String password;

    private String username;

    private String mobile;

    private Authority authority;
}