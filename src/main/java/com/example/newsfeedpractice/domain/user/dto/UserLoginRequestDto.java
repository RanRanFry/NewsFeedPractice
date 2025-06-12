package com.example.newsfeedpractice.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserLoginRequestDto {

    private String email;
    private String password;

}

