package com.eriksgda.hotel_management.controller.customer;

import com.eriksgda.hotel_management.model.RoomsResponseDTO;
import com.eriksgda.hotel_management.service.customer.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms/available/{pageNumber}")
    public ResponseEntity<?> getAvailableRooms(@PathVariable Integer pageNumber) {
        try {
            RoomsResponseDTO response = this.roomService.getAvailableRooms(pageNumber);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
}
