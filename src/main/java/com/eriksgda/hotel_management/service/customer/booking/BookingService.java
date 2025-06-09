package com.eriksgda.hotel_management.service.customer.booking;

import com.eriksgda.hotel_management.model.ReservationRequestDTO;
import com.eriksgda.hotel_management.model.ReservationResponseDTO;
import com.eriksgda.hotel_management.model.ReservationsResponseDTO;

import java.util.UUID;

public interface BookingService {
    ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO);
    ReservationsResponseDTO getAllReservationsByUserId(UUID id, int pageNumber);
}
