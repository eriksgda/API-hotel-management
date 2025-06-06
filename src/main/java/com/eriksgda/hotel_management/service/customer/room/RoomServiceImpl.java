package com.eriksgda.hotel_management.service.customer.room;

import com.eriksgda.hotel_management.entity.Room;
import com.eriksgda.hotel_management.model.RoomResponseDTO;
import com.eriksgda.hotel_management.model.RoomsResponseDTO;
import com.eriksgda.hotel_management.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Override
    public RoomsResponseDTO getAvailableRooms(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<Room> rooms = this.roomRepository.findByAvailable(true, pageable);

        return new RoomsResponseDTO(
                rooms.stream().map(RoomResponseDTO::fromEntity).collect(Collectors.toList()),
                rooms.getTotalPages(),
                rooms.getPageable().getPageNumber());
    }
}
