package org.example.springpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.dto.MovieRequest;
import org.example.springpractice.dto.MovieResponse;
import org.example.springpractice.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies")
    public MovieResponse saveMovie(
            @RequestBody MovieRequest request
    ) {
        return ResponseEntity.ok(movieService.save(request));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> findAllMovies() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponse> findOneMovie(
            @PathVariable Long movieId
    ) {
        return ResponseEntity.ok(movieService.findOne(movieId));
    }

    @PatchMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponse> updateMovie(
            @PathVariable Long movieId,
            @RequestBody MovieRequest request
    ) {
        return ResponseEntity.ok(movieService.update(movieId, request));
    }
}
