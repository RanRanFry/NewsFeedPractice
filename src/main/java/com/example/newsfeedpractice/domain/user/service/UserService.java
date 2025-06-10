package com.example.newsfeedpractice.domain.user.service;

import com.example.newsfeedpractice.domain.user.dto.UserCreateRequestDto;
import com.example.newsfeedpractice.domain.user.dto.UserCreateResponseDto;
import com.example.newsfeedpractice.domain.user.dto.UserLoginRequestDto;
import com.example.newsfeedpractice.domain.user.dto.UserLoginResponseDto;


public interface UserService {

     UserCreateResponseDto createUserAccount(UserCreateRequestDto userAccountRequestDto);

     UserLoginResponseDto userLogin(UserLoginRequestDto logintRequest);
}
