package com.thxforservice.reservation.services;

import com.thxforservice.reservation.entities.GroupProgram;
import com.thxforservice.reservation.entities.GroupReserProgram;
import com.thxforservice.reservation.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationApplyService {

    private final ReservationRepository reservationRepository;

}
