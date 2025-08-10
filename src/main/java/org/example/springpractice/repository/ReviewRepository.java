package org.example.springpractice.repository;

import org.example.springpractice.entity.Movie;
import org.example.springpractice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMovie(Movie movie);
}
