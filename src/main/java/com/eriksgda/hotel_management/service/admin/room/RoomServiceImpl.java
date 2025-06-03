package com.eriksgda.hotel_management.service.admin.room;

import com.eriksgda.hotel_management.entity.Room;
import com.eriksgda.hotel_management.model.CreateRoomRequestDTO;
import com.eriksgda.hotel_management.model.CreateRoomResponseDTO;
import com.eriksgda.hotel_management.model.RoomResponseDTO;
import com.eriksgda.hotel_management.model.RoomsResponseDTO;
import com.eriksgda.hotel_management.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public CreateRoomResponseDTO createRoom(CreateRoomRequestDTO dto) {
        if (dto == null) {
            throw  new IllegalArgumentException("Room data must not be null.");
        }

        if (roomRepository.existsRoomByName(dto.name())) {
            throw new IllegalArgumentException("A room whit this name already exists.");
        }

        if (dto.price() <= 0) {
            throw new IllegalArgumentException("Price need to be higher than 0.");
        }

        Room room = Room.builder()
                .name(dto.name())
                .type(dto.type())
                .price(dto.price())
                .available(true)
                .build();

        Room newRoom = this.roomRepository.save(room);

        return CreateRoomResponseDTO.fromEntity(newRoom);
    }

    @Override
    public RoomsResponseDTO getAllRooms(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<Room> rooms = this.roomRepository.findAll(pageable);

        return new RoomsResponseDTO(
                rooms.stream().map(RoomResponseDTO::fromEntity).collect(Collectors.toList()),
                rooms.getTotalPages(),
                rooms.getPageable().getPageNumber());
    }

    @Override
    public RoomResponseDTO getRoomById(UUID id) {
        Optional<Room> optionalRoom = this.roomRepository.findById(id);

        if (optionalRoom.isEmpty()) {
            throw new EntityNotFoundException("Room doesn't exist.");
        }

        return RoomResponseDTO.fromEntity(optionalRoom.get());
    }
}
