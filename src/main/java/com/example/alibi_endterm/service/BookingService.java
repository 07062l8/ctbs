package com.example.alibi_endterm.service;

import com.example.alibi_endterm.entity.Booking;
import com.example.alibi_endterm.entity.Movie;
import com.example.alibi_endterm.entity.User;
import com.example.alibi_endterm.repository.BookingRepository;
import com.example.alibi_endterm.repository.MovieRepository;
import com.example.alibi_endterm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private final BookingRepository bookingRepository;

    @Autowired
    private final MovieRepository movieRepository;

    @Autowired
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }


    public Booking createBooking(Long userId, Long movieId, int tickets) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        if (movie.getAvailableTickets() < tickets) {
            throw new RuntimeException("Not enough tickets available");
        }

        movie.setAvailableTickets(movie.getAvailableTickets() - tickets);
        movieRepository.save(movie);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setMovie(movie);
        booking.setTicketsBooked(tickets);

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return bookingRepository.findByUser(user);
    }
}
