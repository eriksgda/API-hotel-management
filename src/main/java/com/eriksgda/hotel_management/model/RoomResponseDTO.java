package com.eriksgda.hotel_management.model;

import com.eriksgda.hotel_management.entity.Room;

import java.util.UUID;

public record RoomResponseDTO(UUID id, String name, String type, Long price, boolean available) {
    public static RoomResponseDTO fromEntity(Room room) {
        return new RoomResponseDTO(
                room.getId(),
                room.getName(),
                room.getType(),
                room.getPrice(),
                room.isAvailable()
        );
    }
}
