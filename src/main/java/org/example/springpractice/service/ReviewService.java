package org.example.springpractice.service;

import lombok.RequiredArgsConstructor;
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
                movie.getTitle(),
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
                            review.getMovie().getTitle(),
                            review.getContent()
                    )
            );
        }
        return dtos;

        // stream
//        return reviews.stream()
//                .map(review -> new ReviewResponse(
//                        review.getId(),
//                        review.getMovie().getTitle()
//                        review.getContent(),
//                )).toList();
    }

    @Transactional(readOnly = true)
    public ReviewResponse findOne(Long movieId, Long reviewId) {
        Review review = reviewRepository.findByMovieIdAndId(movieId, reviewId).orElseThrow(
                () -> new IllegalArgumentException("Movie not found with id")
        );
        return new ReviewResponse(
                review.getId(),
                review.getMovie().getTitle(),
                review.getContent());
    }

    @Transactional
    public ReviewResponse updateReview(Long reviewId, ReviewRequest request) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new IllegalArgumentException("Review not found with id")
        );
        review.updateReview(request.getContent());
        return new ReviewResponse(
                review.getId(),
                review.getMovie().getTitle(),
                review.getContent());
    }
}
