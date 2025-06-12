package com.example.newsfeedpractice.domain.post.dto;

import com.example.newsfeedpractice.domain.post.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class PostCreateResponseDto {
    private Long id;
    private String postTitle;
    private String postContent;
    private String postImageUrl;
    private String nickname;
    private String profileUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostCreateResponseDto(Post post) {
        this.id = post.getId();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.postImageUrl = post.getPostImageUrl();
        this.nickname = post.getUser().getNickname();
        this.profileUrl = post.getUser().getProfileUrl();
    }
}
