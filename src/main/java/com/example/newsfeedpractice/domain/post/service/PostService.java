package com.example.newsfeedpractice.domain.post.service;

import com.example.newsfeedpractice.domain.post.Entity.Post;
import com.example.newsfeedpractice.domain.post.dto.PageNumberGetRequestDto;
import com.example.newsfeedpractice.domain.post.dto.PostCreateResponseDto;
import com.example.newsfeedpractice.domain.post.dto.CreatePostRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

public interface PostService {
    PostCreateResponseDto createPost(CreatePostRequestDTO createRequest, HttpServletRequest request);

    Page<Post> getPostList(PageNumberGetRequestDto pageRequest);

    PostCreateResponseDto updatePost(Long id,CreatePostRequestDTO updatePostRequest, HttpServletRequest request);

    void deletePost(Long id, HttpServletRequest request);


}
