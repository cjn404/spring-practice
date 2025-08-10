package org.example.springpractice.service;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.dto.MovieRequest;
import org.example.springpractice.dto.MovieResponse;
import org.example.springpractice.entity.Movie;
import org.example.springpractice.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public MovieResponse save(MovieRequest request) {
        Movie movie = new Movie(request.getTitle());
        Movie savedMovie = movieRepository.save(movie);
        return new MovieResponse(
                savedMovie.getId(),
                savedMovie.getTitle()
        );
    }

    @Transactional(readOnly = true)
    public List<MovieResponse> findAll() {
        List<Movie> movies = movieRepository.findAll();
        // 향상된 for문
//        List<MovieResponse> responses = new ArrayList<>();
//        for (Movie movie : movies) {
//            MovieResponse response = new MovieResponse(
//                    movie.getId(),
//                    movie.getTitle()
//            );
//            responses.add(response);
//        }
//
//        return responses;

        // stream
        return movies.stream()
                .map(movie -> new MovieResponse(
                        movie.getId(),
                        movie.getTitle()
                )).toList();
    }
}
