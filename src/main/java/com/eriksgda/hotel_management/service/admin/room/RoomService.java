package com.eriksgda.hotel_management.service.admin.room;

import com.eriksgda.hotel_management.model.*;

import java.util.UUID;

public interface RoomService {
    CreateRoomResponseDTO createRoom(CreateRoomRequestDTO createRoomRequestDTO);
    RoomsResponseDTO getAllRooms(Integer pageNumber);
    RoomResponseDTO getRoomById(UUID id);
    RoomResponseDTO updateRoom(UUID id, UpdateRequestDTO updateRequestDTO);
    void deleteRoom(UUID id);
}
