package com.example.newsfeedpractice.domain.post.Entity;

import com.example.newsfeedpractice.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Posts {
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

    @Column(name ="created_at", nullable =false)
    private LocalDateTime createdAt;

    @Column(name ="modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    public Posts(User user, String postTitle, String postContent){
        this.user = user;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }
}
