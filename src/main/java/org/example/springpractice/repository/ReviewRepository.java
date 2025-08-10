package org.example.springpractice.repository;

import org.example.springpractice.entity.Movie;
import org.example.springpractice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMovie(Movie movie);

    Optional<Review> findByMovieIdAndId(Long movieId, Long id);
}
