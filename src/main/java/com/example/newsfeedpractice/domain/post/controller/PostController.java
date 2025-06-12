package com.example.newsfeedpractice.domain.post.controller;

import com.example.newsfeedpractice.domain.post.Entity.Post;
import com.example.newsfeedpractice.domain.post.dto.GetPostResponseDto;
import com.example.newsfeedpractice.domain.post.dto.PageNumberGetRequestDto;
import com.example.newsfeedpractice.domain.post.dto.PostCreateResponseDto;
import com.example.newsfeedpractice.domain.post.dto.CreatePostRequestDTO;
import com.example.newsfeedpractice.domain.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody CreatePostRequestDTO createRequest,
            HttpServletRequest request){
        PostCreateResponseDto newPost = postService.createPost(createRequest, request);
        return new ResponseEntity<>(newPost,HttpStatus.OK);
    }

    /**
     * 게시글 전체 조회
     */
    @GetMapping
    public ResponseEntity<Page <GetPostResponseDto>> getPostList(@RequestBody PageNumberGetRequestDto pageRequest){
        Page<GetPostResponseDto> postList = postService.getPostList(pageRequest);
        return new ResponseEntity <> (postList,HttpStatus.OK);
    }

    /**
     * 게시글 수정
     *
     */
    @PatchMapping("/{id}")
    public ResponseEntity <PostCreateResponseDto> updatePost(@PathVariable Long id,
                           @RequestBody CreatePostRequestDTO updatePostRequest,
                           HttpServletRequest request){
        PostCreateResponseDto postCreateResponseDto = postService.updatePost(id, updatePostRequest, request);
        return new ResponseEntity<> (postCreateResponseDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id, HttpServletRequest request){
        postService.deletePost(id,  request);
    }
}
