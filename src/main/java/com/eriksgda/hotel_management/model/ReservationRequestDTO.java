package com.eriksgda.hotel_management.model;


import java.time.LocalDate;
import java.util.UUID;

public record ReservationRequestDTO(
        UUID id,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        Long price,
        UUID roomId,
        UUID userId
) {}
