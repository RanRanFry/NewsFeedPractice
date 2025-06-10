package com.example.newsfeedpractice.domain.user.service;

import com.example.newsfeedpractice.domain.user.dto.UserCreateRequestDto;
import com.example.newsfeedpractice.domain.user.dto.UserCreateResponseDto;
import com.example.newsfeedpractice.domain.user.dto.UserLoginRequestDto;
import com.example.newsfeedpractice.domain.user.dto.UserLoginResponseDto;
import com.example.newsfeedpractice.domain.user.entity.User;
import com.example.newsfeedpractice.domain.user.repository.UserRepostiory;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Builder //빌더 패턴 사용,  생성자 순서 상관없이 사용 가능
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepostiory userRepository;


    public boolean isVaild(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return email.matches(emailRegex);
    }

    @Override
    public UserCreateResponseDto createUserAccount(UserCreateRequestDto requestDto) {


        String email= requestDto.getEmail();
        if (!isVaild(email)){
            throw new IllegalArgumentException("Invaild email address format");
        }



        User user = User.builder()
                        .userName(requestDto.getUserName())
                .registrationNumber(requestDto.getRegistrationNumber())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .nickname(requestDto.getNickname())
                .phoneNumber(requestDto.getPhoneNumber())
                .profileUrl("https://none^&Image") //profile UrL 디폴트 값으로 넣어주는 것
                .build();

        User newUser =  (User) userRepository.save(user); // save()는 JPA에서 Entity만 저장 가능하다.

        return new UserCreateResponseDto(newUser.getId(), newUser.getEmail(), newUser.getPassword(), newUser.getNickname());

    }

    @Override
    public UserLoginResponseDto userLogin(UserLoginRequestDto logintRequest) {

        User user = userRepository.findByEmail(logintRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("없는 회원입니다."));

        if (!user.getPassword().equals(logintRequest.getPassword())){
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return new UserLoginResponseDto(user.getId(), user.getEmail(), user.getNickname() );
    }


}
