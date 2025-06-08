package com.eriksgda.hotel_management.model;

import com.eriksgda.hotel_management.entity.Reservation;
import com.eriksgda.hotel_management.entity.Room;
import com.eriksgda.hotel_management.entity.User;
import com.eriksgda.hotel_management.enums.ReservationStatus;

import java.time.LocalDate;
import java.util.UUID;

public record ReservationResponseDTO(
        UUID id,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        Long price,
        ReservationStatus reservationStatus,
        UUID roomId,
        String roomType,
        String roomName,
        UUID userId,
        String username
) {
    public static ReservationResponseDTO fromEntity(Reservation reservation) {
        return new ReservationResponseDTO(
                reservation.getId(),
                reservation.getCheckInDate(),
                reservation.getCheckOutDate(),
                reservation.getPrice(),
                reservation.getReservationStatus(),
                reservation.getRoom().getId(),
                reservation.getRoom().getType(),
                reservation.getRoom().getName(),
                reservation.getUser().getId(),
                reservation.getUser().getName()
        );
    }
}
