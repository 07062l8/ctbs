package com.example.alibi_endterm.dto;

import lombok.Data;

@Data
public class BookingDTO {
    private Long userId;
    private Long movieId;
    private int ticketsBooked;
}
