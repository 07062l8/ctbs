package com.example.alibi_endterm.controller;

import com.example.alibi_endterm.dto.MovieDTO;
import com.example.alibi_endterm.entity.Movie;
import com.example.alibi_endterm.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDTO> getAllMovies() {
        return movieService.getAllMovies().stream()
                .map(movie -> {
                    MovieDTO dto = new MovieDTO();
                    dto.setTitle(movie.getTitle());
                    dto.setDescription(movie.getDescription());
                    dto.setAvailableTickets(movie.getAvailableTickets());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
