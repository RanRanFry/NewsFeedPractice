package com.example.newsfeedpractice.domain.user.repository;

import com.example.newsfeedpractice.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepostiory extends JpaRepository <User, Long> {
}
