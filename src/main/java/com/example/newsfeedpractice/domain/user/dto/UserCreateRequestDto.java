package com.example.newsfeedpractice.domain.user.dto;

import lombok.Getter;


@Getter
public class UserCreateRequestDto {

    private String userName;

    private String registrationNumber;

    private String email;

    private String password;

    private String nickname;

    private String phoneNumber;
}
