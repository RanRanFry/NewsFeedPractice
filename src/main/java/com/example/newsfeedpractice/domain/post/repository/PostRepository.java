package com.example.newsfeedpractice.domain.post.repository;

import com.example.newsfeedpractice.domain.post.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post, Long> {
}
