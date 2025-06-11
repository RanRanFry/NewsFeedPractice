package com.example.newsfeedpractice.domain.user.controller;

import com.example.newsfeedpractice.domain.user.dto.*;
import com.example.newsfeedpractice.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor //final 등의 필수 인자의 생성자를 자동 만들어주기
@RestController
@RequestMapping("/user")
public class UserController {

    //RequieredArgsConstructor 어노테이션 덕에 생성자 생성 필요없다.
    private final UserService userService;

    /**
     * 유저 회원가입
     * @param userAccountRequest
     * @return
     */
    @PostMapping("/sign-up")
    public ResponseEntity <UserCreateResponseDto> createUserAccount(
            @RequestBody UserCreateRequestDto userAccountRequest
            //MappingJackson2HttpMessageConverter로 인해서 JSON 값을 매핑해서 바로 DTO로 만든다.
    ){
        UserCreateResponseDto userAccount = userService.createUserAccount(userAccountRequest);
        return new ResponseEntity<>(userAccount, HttpStatus.OK);
    }


    /**
     * 유저 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> userLogin(
            HttpServletRequest request,
            @RequestBody UserLoginRequestDto loginRequest){
        UserLoginResponseDto userLoginResponseDto = userService.userLogin( request, loginRequest);
        return new ResponseEntity<>(userLoginResponseDto, HttpStatus.OK) ;
    }

    /**
     * 본인 프로필 조회
     */
    @GetMapping("/myProfile")
    public ResponseEntity <MyProfileResponseDto> getMyProfile(HttpServletRequest request){
        MyProfileResponseDto myProfile = userService.getMyProfile(request);
        return new ResponseEntity<>(myProfile, HttpStatus.OK);
    }

    /**
     * 유저 프로필 조회(타인)
     * 민감 정보 표시 제한
     * 민감 정보가 아닌 것  : 닉네임, 프로필 URL
     *
     */
    @GetMapping("/{id}")
    public ResponseEntity <ProfileResponseDto>getProfile(@PathVariable Long id){
        ProfileResponseDto profile = userService.getProfile(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }



    /**
     * 수정
     */
    @PatchMapping("/myProfile")
    public ResponseEntity <MyProfileResponseDto> changeInfo(
            HttpServletRequest request,
            @RequestBody ChangeInfoRequestDto changeRequest){
        MyProfileResponseDto newMyProfile = userService.changeMyProfile(request, changeRequest);
        return new ResponseEntity<>(newMyProfile, HttpStatus.OK);
    }




}
