package com.example.newsfeedpractice.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class UserAccountRequestDto {

    private String userName;

    private String registrationNumber;

    private String email;

    private String password;

    private String nickname;

    private String phoneNumber;
}
