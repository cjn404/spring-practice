package org.example.springpractice.dto;

import lombok.Getter;

@Getter
public class ReviewResponse {

    private Long id;
    private String content;

    public ReviewResponse(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
