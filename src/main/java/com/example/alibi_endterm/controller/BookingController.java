package com.example.alibi_endterm.controller;

import com.example.alibi_endterm.dto.BookingDTO;
import com.example.alibi_endterm.entity.Booking;
import com.example.alibi_endterm.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody BookingDTO bookingDTO) {
        bookingService.createBooking(bookingDTO.getUserId(), bookingDTO.getMovieId(), bookingDTO.getTicketsBooked());
        return ResponseEntity.ok("Booking created successfully");
    }

    @GetMapping("/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable Long userId) {
        return bookingService.getBookingsByUser(userId);
    }
}
