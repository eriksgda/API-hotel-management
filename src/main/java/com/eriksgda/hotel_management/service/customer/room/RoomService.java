package com.eriksgda.hotel_management.service.customer.room;

import com.eriksgda.hotel_management.model.RoomsResponseDTO;

public interface RoomService {
    RoomsResponseDTO getAvailableRooms(Integer pageNumber);
}
