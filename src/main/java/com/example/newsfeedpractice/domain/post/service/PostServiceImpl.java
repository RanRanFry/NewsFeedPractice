package com.example.newsfeedpractice.domain.post.service;

import com.example.newsfeedpractice.domain.post.Entity.Post;
import com.example.newsfeedpractice.domain.post.dto.PostCreateResponseDto;
import com.example.newsfeedpractice.domain.post.dto.createPostRequestDTO;

import com.example.newsfeedpractice.domain.post.repository.PostRepository;
import com.example.newsfeedpractice.domain.user.entity.User;
import com.example.newsfeedpractice.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Override
    public PostCreateResponseDto createPost(createPostRequestDTO createRequest, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        User loginUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("로그인한 회원만 작성할 수 있습니다."));
        Post newPost = Post.builder()
                .user(loginUser)
                .postTitle(createRequest.getPostTitle())
                .postContent(createRequest.getPostContent())
                .postImageUrl(createRequest.getPostImageUrl())
                .build();

        postRepository.save(newPost);
        PostCreateResponseDto postCreateResponse = PostCreateResponseDto.builder()
                .id(newPost.getId())
                .postTitle(newPost.getPostTitle())
                .postContent(newPost.getPostContent())
                .postImageUrl((newPost.getPostImageUrl()))
                .nickname(newPost.getUser().getNickname())
                .profileUrl(newPost.getUser().getProfileUrl())
                .createdAt(newPost.getCreatedAt())
                .modifiedAt(newPost.getModifiedAt())
                .build();
        return postCreateResponse;

    }


}
