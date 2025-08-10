package org.example.springpractice.service;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.dto.MovieResponse;
import org.example.springpractice.dto.ReviewRequest;
import org.example.springpractice.dto.ReviewResponse;
import org.example.springpractice.entity.Movie;
import org.example.springpractice.entity.Review;
import org.example.springpractice.repository.MovieRepository;
import org.example.springpractice.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    public final ReviewRepository reviewRepository;
    public final MovieRepository movieRepository;

    @Transactional
    public ReviewResponse save(ReviewRequest request, Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                ()->new IllegalArgumentException("해당 movieId의 movie는 없습니다.")
        );
        Review review = new Review(
                request.getContent(),
                movie
        );
        Review savedReview = reviewRepository.save(review);
        return new ReviewResponse(
                savedReview.getId(),
                savedReview.getContent()
        );
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> findAll(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("해당 movieId의 movie는 없습니다.")
        );

        List<Review> reviews = reviewRepository.findAllByMovie(movie);
        List<ReviewResponse> dtos = new ArrayList<>();

        for (Review review : reviews) {
            dtos.add(
                    new ReviewResponse(
                            review.getId(),
                            review.getContent()
                    )
            );
        }
        return dtos;

        // stream
//        return reviews.stream()
//                .map(review -> new ReviewResponse(
//                        review.getId(),
//                        review.getContent()
//                )).toList();
    }
}
