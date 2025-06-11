package com.example.newsfeedpractice.domain.user.dto;

import com.example.newsfeedpractice.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyProfileResponseDto {

    private Long id;

    private String email;

    private String profileUrl;

    private String nickname;

    private String phoneNumber;

    public MyProfileResponseDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.profileUrl = user.getProfileUrl();
        this.nickname = user.getNickname();
        this.phoneNumber = user.getPhoneNumber();
    }

}
