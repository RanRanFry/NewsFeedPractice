package com.example.newsfeedpractice.domain.post.service;

import com.example.newsfeedpractice.domain.post.Entity.Post;
import com.example.newsfeedpractice.domain.post.dto.GetPostListResponseDto;
import com.example.newsfeedpractice.domain.post.dto.PageNumberGetRequestDto;
import com.example.newsfeedpractice.domain.post.dto.PostCreateResponseDto;
import com.example.newsfeedpractice.domain.post.dto.CreatePostRequestDTO;

import com.example.newsfeedpractice.domain.post.repository.PostRepository;
import com.example.newsfeedpractice.domain.user.entity.User;
import com.example.newsfeedpractice.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Override
    public PostCreateResponseDto createPost(CreatePostRequestDTO createRequest, HttpServletRequest request) {

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
                .nickname(newPost.getUser().getNickname())//N
                .profileUrl(newPost.getUser().getProfileUrl())
                .createdAt(newPost.getCreatedAt())
                .modifiedAt(newPost.getModifiedAt())
                .build();
        return postCreateResponse;

    }

    @Override
    public Page <Post> getPostList(PageNumberGetRequestDto request)
    {
        int pageNumber =  request.getPageNumber();
        List<Post> postList = postRepository.findAll();
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("createdAt"));
        return postRepository.findAll(pageable);
        //ToDo: 출력시 user 정보 모두 출력되는 문제 발생 -> 수정필요

    }

    /**
     * 게시글 수정
     */
    @Override
    public PostCreateResponseDto updatePost(Long id, CreatePostRequestDTO updatePostRequest, HttpServletRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("조회되지 않는 글입니다."));

        //로그인한 아이디
        Long userId = (Long) request.getSession().getAttribute("userId");

        //해당 번호 글의 작성자 아이디
        Long ownerId = post.getUser().getId();

        //검증 : 로그인한 사람과 글의 작성자가 일치하는지
        if(!userId.equals(ownerId)){
            throw new IllegalArgumentException("본인 글만 수정 가능합니다.");
        }

        String newPostTitle = updatePostRequest.getPostTitle();
        if(updatePostRequest.getPostTitle() != null && !updatePostRequest.getPostTitle().equals("") ){
            post.changePostTitle(newPostTitle);
        }

        String newPostContent = updatePostRequest.getPostContent();
        if(updatePostRequest.getPostContent() != null && !updatePostRequest.getPostContent().equals("") ){
            post.changePostContent(newPostContent);
        }

        //이미지는 빠져도 되므로 null 값이 되어도 상관없다.
        String newPostImageUrl = updatePostRequest.getPostImageUrl();
        post.changePostTitle(newPostImageUrl);

        Post updatedPost = Post.builder()
                .user(post.getUser())
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .postImageUrl(post.getPostImageUrl())
                .build();

        Post savedPost = postRepository.save(updatedPost);

        PostCreateResponseDto updatedResponseDto = new PostCreateResponseDto(savedPost);
        return updatedResponseDto;
    }

    /**
     * 게시글 삭제
     * @param id
     * @param request
     */
    @Override
    public void deletePost(Long id, HttpServletRequest request) {
        Post deletePost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("조회되지 않는 글입니다."));

        //로그인한 아이디
        Long userId = (Long) request.getSession().getAttribute("userId");

        //해당 번호 글의 작성자 아이디
        Long ownerId = post.getUser().getId();

        //검증 : 로그인한 사람과 글의 작성자가 일치하는지
        if(!userId.equals(ownerId)){
            throw new IllegalArgumentException("본인 글만 삭제 가능합니다.");
        }

        //삭제할 post entity;
        postRepository.delete(deletePost);
    }


}
