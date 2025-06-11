package com.example.newsfeedpractice.domain.user.service;

import com.example.newsfeedpractice.domain.user.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;


public interface UserService {

     UserCreateResponseDto createUserAccount(UserCreateRequestDto userAccountRequestDto);

     UserLoginResponseDto userLogin( HttpServletRequest request, UserLoginRequestDto loginRequest);

     ProfileResponseDto getProfile(Long id);

     MyProfileResponseDto getMyProfile(HttpServletRequest request);

     MyProfileResponseDto changeMyProfile(HttpServletRequest request , ChangeInfoRequestDto changeRequest);

}
