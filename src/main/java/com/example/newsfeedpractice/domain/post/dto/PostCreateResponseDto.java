package com.example.newsfeedpractice.domain.post.dto;

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

}
