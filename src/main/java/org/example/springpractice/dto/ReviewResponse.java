package org.example.springpractice.dto;

import lombok.Getter;

@Getter
public class ReviewResponse {

    private final Long id;
    private final String movieTitle;
    private final String content;

    public ReviewResponse(Long id, String movieTitle, String content) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.content = content;
    }
}
