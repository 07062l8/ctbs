package com.example.alibi_endterm.repository;

import com.example.alibi_endterm.entity.Booking;
import com.example.alibi_endterm.entity.Movie;
import com.example.alibi_endterm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitle(String name);

}
