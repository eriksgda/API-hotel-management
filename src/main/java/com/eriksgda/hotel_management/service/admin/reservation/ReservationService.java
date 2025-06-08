package com.eriksgda.hotel_management.service.admin.reservation;

import com.eriksgda.hotel_management.model.ReservationsResponseDTO;

public interface ReservationService {
    ReservationsResponseDTO getAllReservations(int pageNumber);
}
