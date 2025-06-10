package com.example.newsfeedpractice.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserLoginResponseDto {
    private Long id;
    private String email;
    private String nickname;

}
