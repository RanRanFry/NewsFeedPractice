package com.example.newsfeedpractice.domain.user.service;

import com.example.newsfeedpractice.domain.user.dto.UserAccountRequestDto;
import com.example.newsfeedpractice.domain.user.dto.UserCreateResponseDto;
import org.springframework.stereotype.Service;


public interface UserService {

     UserCreateResponseDto createUserAccount(UserAccountRequestDto userAccountRequestDto);
}
