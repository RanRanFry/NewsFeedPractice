package com.example.newsfeedpractice.domain.post.Entity;

import com.example.newsfeedpractice.domain.common.BaseEntity;
import com.example.newsfeedpractice.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "posts")
public class Post extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "post_title", nullable =false)
    private String postTitle;

    @Column(name = "post_content", nullable = false)
    private String postContent;

    @Column(name ="post_image_url")
    private String postImageUrl;


    @Builder
    public Post(User user, String postTitle, String postContent, String postImageUrl){
        this.user = user;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postImageUrl = postImageUrl;

    }
}
