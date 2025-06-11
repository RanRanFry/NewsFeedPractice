package com.example.newsfeedpractice.domain.user.service;

import com.example.newsfeedpractice.domain.user.dto.*;


public interface UserService {

     UserCreateResponseDto createUserAccount(UserCreateRequestDto userAccountRequestDto);
     ProfileResponseDto getProfile(Long id);

}
