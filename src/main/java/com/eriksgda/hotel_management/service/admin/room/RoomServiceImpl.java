package com.eriksgda.hotel_management.service.admin.room;

import com.eriksgda.hotel_management.entity.Room;
import com.eriksgda.hotel_management.model.CreateRoomRequestDTO;
import com.eriksgda.hotel_management.model.CreateRoomResponseDTO;
import com.eriksgda.hotel_management.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
