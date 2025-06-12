package com.example.newsfeedpractice.domain.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateResponseDto {

    private Long id;

    private String email;

    private String password;

    private String nickname;


}
