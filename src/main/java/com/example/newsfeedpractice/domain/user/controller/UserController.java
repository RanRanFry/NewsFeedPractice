package com.example.newsfeedpractice.domain.user.controller;

import com.example.newsfeedpractice.domain.user.dto.UserAccountRequestDto;
import com.example.newsfeedpractice.domain.user.dto.UserCreateResponseDto;
import com.example.newsfeedpractice.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestBody UserAccountRequestDto userAccountRequest
            //MappingJackson2HttpMessageConverter로 인해서 JSON 값을 매핑해서 바로 DTO로 만든다.
    ){
        UserCreateResponseDto userAccount = userService.createUserAccount(userAccountRequest);
        return new ResponseEntity<>(userAccount, HttpStatus.OK);
    }


    /**
     * 유저 로그인
     */
    public void userLogin(@RequestBody){

    }



}
