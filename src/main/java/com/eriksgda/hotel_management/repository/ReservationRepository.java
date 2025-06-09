package com.eriksgda.hotel_management.repository;

import com.eriksgda.hotel_management.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    Page<Reservation> findAllByUserId(UUID userId, Pageable pageable);
}
