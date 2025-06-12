package com.example.newsfeedpractice.domain.post.dto;

import lombok.Getter;

@Getter
public class createPostRequestDTO {
    private String postTitle;
    private String postContent;
    private String postImageUrl;
}
