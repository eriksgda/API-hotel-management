package com.eriksgda.hotel_management.model;

import java.util.List;

public record RoomsResponseDTO(List<CreateRoomResponseDTO> RoomsList, Integer totalPages, Integer pageNumber) {
}
