package com.example.newsfeedpractice.domain.user.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor //기본 생성자 자동 생성
@AllArgsConstructor //전체 생성자 자동 생성
@Builder // 빌더 패턴 자동 구현
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "user_name", unique = true)
    private String userName;

    @Column(nullable = false,  name = "registration_number", unique = true)
    private String registrationNumber;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "profile_image_url")
    private String profileUrl;

    @Column(nullable = false,name = "nickname", unique = true)
    private String nickname;

    @Column(nullable = false,name = "phone_number", unique = true)
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getNickname() {
        return nickname;
    }
}


