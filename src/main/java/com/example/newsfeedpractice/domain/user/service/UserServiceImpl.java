package com.example.newsfeedpractice.domain.user.service;

import com.example.newsfeedpractice.domain.config.PasswordEncoder;
import com.example.newsfeedpractice.domain.user.dto.*;
import com.example.newsfeedpractice.domain.user.entity.User;
import com.example.newsfeedpractice.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


@Builder //빌더 패턴 사용,  생성자 순서 상관없이 사용 가능
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
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
    public UserCreateResponseDto createUserAccount( UserCreateRequestDto requestDto) {

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

    //유저 프로필 조회
    @Override
    public ProfileResponseDto getProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("없는 회원입니다."));

        return new ProfileResponseDto(user.getNickname(), user.getProfileUrl());
    }


    @Override
    public UserLoginResponseDto userLogin( HttpServletRequest request, UserLoginRequestDto logintRequest) {

        //새로 접속하니까 기존 쿠키 삭제 로직
        HttpSession oldSession = request.getSession();
        oldSession.invalidate(); //구 세션 삭제
        //이메일 검증
        User user = userRepository.findByEmail(logintRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("없는 회원입니다."));

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


    @Override
    public MyProfileResponseDto getMyProfile(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long myId = (Long) session.getAttribute("userId");
        User user = userRepository.findById(myId)
                .orElseThrow(() -> new RuntimeException("조회되지 않는 회원입니다."));

        MyProfileResponseDto myProfileResponseDto = new MyProfileResponseDto(user);
        return myProfileResponseDto;

    }

    @Override
    public MyProfileResponseDto changeMyProfile(HttpServletRequest request, ChangeInfoRequestDto changeRequest) {

        HttpSession session = request.getSession();
        Long myId = (Long) session.getAttribute("userId");
        User user = userRepository.findById(myId)
                .orElseThrow(() -> new RuntimeException("조회되지 않는 회원입니다."));

        String newPassword = changeRequest.getPassword();
        String newNickname = changeRequest.getNickname();
        String newProfileUrl = changeRequest.getProfileUrl();
        String newPhoneNumber = changeRequest.getPhoneNumber();

        if (newPassword != null && !newPassword.isBlank() ){

            if(!user.getPassword().equals(changeRequest.getVerifyPassword())){
                throw new IllegalArgumentException("틀린 비밀번호입니다. 다시 입력해주세요. ");
            }

            if(user.getPassword().equals(changeRequest.getPassword())){
                throw new IllegalArgumentException("동일한 비밀번호입니다. 새로 입력해주세요 ");
            }

            if(!isValidPw(changeRequest.getPassword())){
                throw new IllegalArgumentException("지원되지 않는 비밀번호 양식입니다.");
            }
            user.changePassword(newPassword);
        }

        //닉네임 변경
        if(newNickname != null && !newNickname.isBlank()){
            user.changeNickname(newNickname);
        }

       //프로필 이미지 변경 URL
        if(newProfileUrl != null && !newProfileUrl.isBlank()){
            user.changeProfileUrl(newProfileUrl);
        }

        //전화번호 변경
        if(newPhoneNumber != null && !newPhoneNumber.isBlank()){
            user.changePhoneNumber(newPhoneNumber);
        }
        userRepository.save(user);

        MyProfileResponseDto changeProfileResponse = new MyProfileResponseDto(user);

        return changeProfileResponse;
    }


}
