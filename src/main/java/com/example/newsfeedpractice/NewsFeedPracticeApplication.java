package com.example.newsfeedpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class NewsFeedPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsFeedPracticeApplication.class, args);
    }

}
