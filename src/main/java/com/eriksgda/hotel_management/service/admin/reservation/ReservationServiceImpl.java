package com.eriksgda.hotel_management.service.admin.reservation;

import com.eriksgda.hotel_management.entity.Reservation;
import com.eriksgda.hotel_management.entity.Room;
import com.eriksgda.hotel_management.enums.ReservationStatus;
import com.eriksgda.hotel_management.model.ReservationResponseDTO;
import com.eriksgda.hotel_management.model.ReservationsResponseDTO;
import com.eriksgda.hotel_management.repository.ReservationRepository;
import com.eriksgda.hotel_management.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    private final RoomRepository roomRepository;

    private static final int SEARCH_RESULT_PER_PAGE = 4;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public ReservationsResponseDTO getAllReservations(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);

        Page<Reservation> reservationPage = reservationRepository.findAll(pageable);

        return new ReservationsResponseDTO(
                reservationPage.getTotalPages(),
                reservationPage.getPageable().getPageNumber(),
                reservationPage.stream().map(ReservationResponseDTO::fromEntity).collect(Collectors.toList())
        );
    }

    @Override
    public ReservationResponseDTO changeReservationStatus(UUID id, String status) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Reservation doesn't exists.")
        );

        if (ReservationStatus.APPROVED.name().equalsIgnoreCase(status)) {
            reservation.setReservationStatus(ReservationStatus.APPROVED);
        } else {
            reservation.setReservationStatus(ReservationStatus.REJECTED);
        }

        Reservation reservationChanged = this.reservationRepository.save(reservation);

        Room room = reservationChanged.getRoom();
        room.setAvailable(false);

        this.roomRepository.save(room);

        return ReservationResponseDTO.fromEntity(reservationChanged);
    }
}
