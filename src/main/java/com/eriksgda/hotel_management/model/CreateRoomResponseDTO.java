package com.eriksgda.hotel_management.model;

import com.eriksgda.hotel_management.entity.Room;

import java.util.UUID;

public record CreateRoomResponseDTO(UUID id, String name, String type, Long price, boolean available) {
    public static CreateRoomResponseDTO fromEntity(Room room) {
        return new CreateRoomResponseDTO(
                room.getId(),
                room.getName(),
                room.getType(),
                room.getPrice(),
                room.isAvailable()
        );
    }
}
