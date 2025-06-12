package com.example.newsfeedpractice.domain.post.dto;

import com.example.newsfeedpractice.domain.post.Entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetPostResponseDto {
    private Long id;
    private String nickname;
    private String profileUrl;
    private String postTitle;
    private String postContent;
    private String postImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public GetPostResponseDto(Post post) {
        this.id = post.getId();
        this.nickname = post.getUser().getNickname();
        this.profileUrl = post.getUser().getProfileUrl();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.postImageUrl = post.getPostImageUrl();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
