package com.example.newsfeedpractice.domain.user.repository;

import com.example.newsfeedpractice.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);


}
