package com.eriksgda.hotel_management.model;

import java.util.List;

public record ReservationsResponseDTO(
        Integer totalPages,
        Integer pageNumber,
        List<ReservationResponseDTO> reservations) {
}
