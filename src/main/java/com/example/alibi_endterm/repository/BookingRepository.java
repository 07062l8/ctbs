package com.example.alibi_endterm.repository;

import com.example.alibi_endterm.entity.Booking;
import com.example.alibi_endterm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}
