package com.eriksgda.hotel_management.service.admin.room;

import com.eriksgda.hotel_management.model.CreateRoomRequestDTO;
import com.eriksgda.hotel_management.model.CreateRoomResponseDTO;
import com.eriksgda.hotel_management.model.RoomsResponseDTO;

public interface RoomService {
    CreateRoomResponseDTO createRoom(CreateRoomRequestDTO createRoomRequestDTO);
    RoomsResponseDTO getAllRooms(Integer pageNumber);
}
