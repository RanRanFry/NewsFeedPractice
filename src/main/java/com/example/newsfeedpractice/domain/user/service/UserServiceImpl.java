package com.example.newsfeedpractice.domain.user.service;

import com.example.newsfeedpractice.domain.config.PasswordEncoder;
import com.example.newsfeedpractice.domain.user.dto.UserCreateRequestDto;
import com.example.newsfeedpractice.domain.user.dto.UserCreateResponseDto;
import com.example.newsfeedpractice.domain.user.dto.UserLoginRequestDto;
import com.example.newsfeedpractice.domain.user.dto.UserLoginResponseDto;
import com.example.newsfeedpractice.domain.user.entity.User;
import com.example.newsfeedpractice.domain.user.repository.UserRepostiory;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Builder //빌더 패턴 사용,  생성자 순서 상관없이 사용 가능
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepostiory userRepository;

    //오류 발생, 튜터님한테 물어보기
    private final PasswordEncoder pwEncoder;

    //이메일 형식 체크 메서드
    public boolean isVaildEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return email.matches(emailRegex);
    }

    public boolean isValidPw(String password){
        String pwRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`])(?=.{8,}).*$";

        return  password.matches(pwRegex);
    }

    @Override
    public UserCreateResponseDto createUserAccount(UserCreateRequestDto requestDto) {

        //이메일 형식 검사
        String email= requestDto.getEmail();

        //중복 이메일 검사 로직
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("duplicated email address");
        }


        if (!isVaildEmail(email)){
            throw new IllegalArgumentException("Invaild email address format");
        }

        String pw = requestDto.getPassword();

        if(!isValidPw(pw)){
            throw new IllegalArgumentException("Invaild password format");
        }
        //비밀번호 암호화
        String encodedPw = pwEncoder.encode(requestDto.getPassword());
        //ToDo : user 생성에 bulider 값으로 pw 넣기
        //-> 문제해결 HttpSesssion을 사용하면 바로 발급된다. 서블렛리퀘스트로 대체

        User user = User.builder()
                        .userName(requestDto.getUserName())
                .registrationNumber(requestDto.getRegistrationNumber())
                .email(requestDto.getEmail())
                .password(encodedPw)
                .nickname(requestDto.getNickname())
                .phoneNumber(requestDto.getPhoneNumber())
                .profileUrl("https://none^&Image") //profile UrL 디폴트 값으로 넣어주는 것
                .build();

        User newUser =  (User) userRepository.save(user); // save()는 JPA에서 Entity만 저장 가능하다.



        return new UserCreateResponseDto(newUser.getId(), newUser.getEmail(), pw, newUser.getNickname());

    }



}
