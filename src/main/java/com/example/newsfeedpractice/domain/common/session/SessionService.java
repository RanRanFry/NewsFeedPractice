package com.example.newsfeedpractice.domain.common.session;

import com.example.newsfeedpractice.domain.config.PasswordEncoder;
import com.example.newsfeedpractice.domain.user.dto.UserLoginRequestDto;
import com.example.newsfeedpractice.domain.user.dto.UserLoginResponseDto;
import com.example.newsfeedpractice.domain.user.entity.User;
import com.example.newsfeedpractice.domain.user.repository.UserRepostiory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SessionService {

    private final UserRepostiory userRepository; //조회를 위해 레포지토리 사용
    private final PasswordEncoder pwEncoder;

    //생성자.
    public void PasswordEncoder(){}

    public UserLoginResponseDto userLogin(UserLoginRequestDto logintRequest, HttpServletRequest request) {

        //새로 접속하니까 기존 쿠키 삭제 로직
        HttpSession oldSession = request.getSession();
        oldSession.invalidate(); //구 세션 삭제
        //이메일 검증
        User user = userRepository.findByEmail(logintRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("없는 회원입니다."));

        //비밀번호 검증
        String encodedPw = pwEncoder.encode(logintRequest.getPassword());

        if (user.getPassword().equals(logintRequest.getPassword())){
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }


        HttpSession session = request.getSession(true);
        /**
         * 세션 코드
         * ToDo: 로그인 실패 시에도 세션 쿠키가 발급되는 문제 존재.
         */


        //1. 데이터 준비
//        String userId = String.valueOf(user.getId());

        Long userId =user.getId(); //인증
        String  role = "loginUser";//인가



        //2. 유저 정보 저장
        session.setAttribute("userId", userId);
        session.setAttribute("role", role);

        //세션 유효시간 설정
        session.setMaxInactiveInterval(300);

        //세션 유효시간 조회
        int maxInactiveInterval = session.getMaxInactiveInterval();

        return new UserLoginResponseDto(user.getId(), user.getEmail(), user.getNickname() );
    }

}
