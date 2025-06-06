package com.eriksgda.hotel_management.service.customer.booking;

import com.eriksgda.hotel_management.model.ReservationRequestDTO;
import com.eriksgda.hotel_management.model.ReservationResponseDTO;

public interface BookingService {
    ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO);
}
