package com.eriksgda.hotel_management.service.admin.room;

import com.eriksgda.hotel_management.model.CreateRoomRequestDTO;
import com.eriksgda.hotel_management.model.CreateRoomResponseDTO;
import com.eriksgda.hotel_management.model.RoomResponseDTO;
import com.eriksgda.hotel_management.model.RoomsResponseDTO;

import java.util.UUID;

public interface RoomService {
    CreateRoomResponseDTO createRoom(CreateRoomRequestDTO createRoomRequestDTO);
    RoomsResponseDTO getAllRooms(Integer pageNumber);
    RoomResponseDTO getRoomById(UUID id);
}
