package org.example.springpractice.dto;

import lombok.Getter;

@Getter
public class ReviewResponse {

    private Long id;
    private String movieTitle;
    private String content;

    public ReviewResponse(Long id, String title, String content) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.content = content;
    }
}
