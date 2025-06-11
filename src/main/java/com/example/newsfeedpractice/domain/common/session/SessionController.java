package com.example.newsfeedpractice.domain.common.session;

import com.example.newsfeedpractice.domain.user.dto.UserLoginRequestDto;
import com.example.newsfeedpractice.domain.user.dto.UserLoginResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;


@AllArgsConstructor
@RestController
@RequestMapping
public class SessionController {

    private final SessionService sessionService;

    /**
     * 유저 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> userLogin(
            HttpServletRequest request,
            @RequestBody UserLoginRequestDto loginRequest){
        UserLoginResponseDto userLoginResponseDto = sessionService.userLogin(loginRequest, request);
        return new ResponseEntity<>(userLoginResponseDto, HttpStatus.OK) ;
    }
}
