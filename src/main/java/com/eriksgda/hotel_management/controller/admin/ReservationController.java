package com.eriksgda.hotel_management.controller.admin;

import com.eriksgda.hotel_management.model.ReservationResponseDTO;
import com.eriksgda.hotel_management.model.ReservationsResponseDTO;
import com.eriksgda.hotel_management.service.admin.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/all/{pageNumber}")
    public ResponseEntity<?> getAllReservations(@PathVariable Integer pageNumber) {
        try {
            ReservationsResponseDTO response = this.reservationService.getAllReservations(pageNumber);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> changeReservationStatus(@PathVariable UUID id, @RequestBody String status) {
        try {
            ReservationResponseDTO response = this.reservationService.changeReservationStatus(id, status);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
}
