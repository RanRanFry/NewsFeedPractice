package com.example.newsfeedpractice.domain.post.dto;

import com.example.newsfeedpractice.domain.post.Entity.Post;

import java.util.List;


public class GetPostListResponseDto {
    private List<Post> postList;

    public GetPostListResponseDto(List<Post> postList) {
        this.postList = postList;
    }

    public List<Post> getPostList() {
        return postList;
    }
}
