package com.example.newsfeedpractice.domain.user.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileResponseDto {
    private String nickname;

    private String profileUrl;

    public ProfileResponseDto(){}



}
