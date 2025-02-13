package com.example.alibi_endterm.service;

import com.example.alibi_endterm.dto.MovieDTO;
import com.example.alibi_endterm.entity.Movie;
import com.example.alibi_endterm.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie addMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setAvailableTickets(movieDTO.getAvailableTickets());
        return movieRepository.save(movie);
    }
}
