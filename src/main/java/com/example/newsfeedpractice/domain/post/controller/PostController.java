package com.example.newsfeedpractice.domain.post.controller;

import com.example.newsfeedpractice.domain.post.dto.PostCreateResponseDto;
import com.example.newsfeedpractice.domain.post.dto.createPostRequestDTO;
import com.example.newsfeedpractice.domain.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
    //속성
    private final PostService postService;


    /**
     * 게시글 생성
     */
    @PostMapping("/create")
    public ResponseEntity <PostCreateResponseDto> createPost(
            @RequestBody createPostRequestDTO createRequest,
            HttpServletRequest request){
        PostCreateResponseDto newPost = postService.createPost(createRequest, request);
        return new ResponseEntity<>(newPost,HttpStatus.OK);
    }

    /**
     * 게시글 조회
     */
}
