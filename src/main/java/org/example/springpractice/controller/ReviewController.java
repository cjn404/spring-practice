package org.example.springpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.dto.ReviewRequest;
import org.example.springpractice.dto.ReviewResponse;
import org.example.springpractice.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/movies/{movieId}/reviews")
    public ResponseEntity<ReviewResponse> saveReview(
            @PathVariable Long movieId,
            @RequestBody ReviewRequest request
    ) {
        return ResponseEntity.ok(reviewService.save(request, movieId));
    }

    @GetMapping("/movies/{moviesId}/reviews")
    public ResponseEntity<List<ReviewResponse>> findAllReviews(
            @PathVariable Long moviesId
    ) {
        return ResponseEntity.ok(reviewService.findAll(moviesId));
    }
}
