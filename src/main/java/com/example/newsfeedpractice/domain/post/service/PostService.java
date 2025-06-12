package com.example.newsfeedpractice.domain.post.service;

import com.example.newsfeedpractice.domain.post.dto.PostCreateResponseDto;
import com.example.newsfeedpractice.domain.post.dto.createPostRequestDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface PostService {
    PostCreateResponseDto createPost(createPostRequestDTO createRequest, HttpServletRequest request);
}
