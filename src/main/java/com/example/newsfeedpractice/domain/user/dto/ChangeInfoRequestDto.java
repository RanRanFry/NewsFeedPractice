package com.example.newsfeedpractice.domain.user.dto;

import lombok.Getter;

@Getter
public class ChangeInfoRequestDto {
    private String nickname;
    private String password;
    private String profileUrl;
    private String phoneNumber;
    private String verifyPassword;
}
