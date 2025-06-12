package com.example.newsfeedpractice.domain.post.dto;

import lombok.Getter;

@Getter
public class CreatePostRequestDTO {
    private String postTitle;
    private String postContent;
    private String postImageUrl;
}
