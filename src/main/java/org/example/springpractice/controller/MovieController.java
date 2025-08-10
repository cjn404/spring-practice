package org.example.springpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.dto.MovieRequest;
import org.example.springpractice.dto.MovieResponse;
import org.example.springpractice.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<MovieResponse> saveMovie(
            @RequestBody MovieRequest request
    ) {
        return ResponseEntity.ok(movieService.save(request));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> findAllMovies() {
        return ResponseEntity.ok(movieService.findAll());
    }
}
