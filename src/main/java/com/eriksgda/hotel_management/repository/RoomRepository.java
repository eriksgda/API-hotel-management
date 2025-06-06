package com.eriksgda.hotel_management.repository;

import com.eriksgda.hotel_management.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface RoomRepository extends JpaRepository<Room, UUID> {
    boolean existsRoomByName(String name);
    Page<Room> findByAvailable(boolean available, Pageable pageable);
}
