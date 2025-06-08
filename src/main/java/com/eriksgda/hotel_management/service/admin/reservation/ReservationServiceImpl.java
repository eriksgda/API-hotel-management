package com.eriksgda.hotel_management.service.admin.reservation;

import com.eriksgda.hotel_management.entity.Reservation;
import com.eriksgda.hotel_management.model.ReservationResponseDTO;
import com.eriksgda.hotel_management.model.ReservationsResponseDTO;
import com.eriksgda.hotel_management.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    private static final int SEARCH_RESULT_PER_PAGE = 4;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
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
}
