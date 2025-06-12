package com.example.newsfeedpractice.domain.post.repository;

import com.example.newsfeedpractice.domain.post.Entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post, Long> {
    Page<Post> findAll(Pageable pagaeable);
}
