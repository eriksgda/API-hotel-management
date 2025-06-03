package com.eriksgda.hotel_management.model;

import java.util.List;

public record RoomsResponseDTO(List<RoomResponseDTO> RoomsList, Integer totalPages, Integer pageNumber) {
}
