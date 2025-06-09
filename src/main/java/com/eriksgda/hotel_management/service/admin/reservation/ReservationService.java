package com.eriksgda.hotel_management.service.admin.reservation;

import com.eriksgda.hotel_management.model.ReservationResponseDTO;
import com.eriksgda.hotel_management.model.ReservationsResponseDTO;

import java.util.UUID;

public interface ReservationService {
    ReservationsResponseDTO getAllReservations(int pageNumber);
    ReservationResponseDTO changeReservationStatus(UUID id, String status);
}
