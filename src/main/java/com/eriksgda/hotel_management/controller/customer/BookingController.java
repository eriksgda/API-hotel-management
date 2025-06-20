package com.eriksgda.hotel_management.controller.customer;

import com.eriksgda.hotel_management.model.ReservationRequestDTO;
import com.eriksgda.hotel_management.model.ReservationResponseDTO;
import com.eriksgda.hotel_management.model.ReservationsResponseDTO;
import com.eriksgda.hotel_management.service.customer.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/customer")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/rooms/reservation")
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequestDTO data) {
        try {
            ReservationResponseDTO response = this.bookingService.createReservation(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @GetMapping("/rooms/reservation/all/{pageNumber}/{id}")
    public ResponseEntity<?> getAllReservationsByUserId(@PathVariable int pageNumber, @PathVariable UUID id) {
        try {
            ReservationsResponseDTO response = this.bookingService.getAllReservationsByUserId(id, pageNumber);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
}
