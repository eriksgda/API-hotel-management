package com.eriksgda.hotel_management.service.customer.booking;

import com.eriksgda.hotel_management.entity.Reservation;
import com.eriksgda.hotel_management.entity.Room;
import com.eriksgda.hotel_management.entity.User;
import com.eriksgda.hotel_management.enums.ReservationStatus;
import com.eriksgda.hotel_management.model.ReservationRequestDTO;
import com.eriksgda.hotel_management.model.ReservationResponseDTO;
import com.eriksgda.hotel_management.repository.ReservationRepository;
import com.eriksgda.hotel_management.repository.RoomRepository;
import com.eriksgda.hotel_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    private final ReservationRepository reservationRepository;

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    @Autowired
    public BookingServiceImpl(ReservationRepository reservationRepository,
                              UserRepository userRepository,
                              RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }


    @Override
    public ReservationResponseDTO createReservation(ReservationRequestDTO dto) {
        Optional<User> optionalUser = this.userRepository.findById(dto.userId());
        Optional<Room> optionalRoom = this.roomRepository.findById(dto.roomId());

        if (optionalUser.isPresent() && optionalRoom.isPresent()) {
            Long days = ChronoUnit.DAYS.between(dto.checkInDate(), dto.checkOutDate());

            Reservation reservation = Reservation.builder()
                    .checkInDate(dto.checkInDate())
                    .checkOutDate(dto.checkOutDate())
                    .price(optionalRoom.get().getPrice() * days)
                    .reservationStatus(ReservationStatus.PENDING)
                    .room(optionalRoom.get())
                    .user(optionalUser.get())
                    .build();

            Reservation res = this.reservationRepository.save(reservation);

            return ReservationResponseDTO.fromEntity(res);
        }

        throw new IllegalArgumentException("error");
    }
}
