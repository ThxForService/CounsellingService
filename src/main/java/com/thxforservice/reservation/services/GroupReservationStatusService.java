package com.thxforservice.reservation.services;

import com.thxforservice.reservation.entities.GroupReserProgram;
import com.thxforservice.reservation.repositories.GroupReserProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupReservationStatusService {

    private final GroupReserProgramRepository reservationRepository;

    public void updateApprovalStatus(Long reservationId, Boolean approval) {
        GroupReserProgram reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        // 기존 상태와 동일하면 처리하지 않음
        if (Boolean.TRUE.equals(reservation.getApproval()) == Boolean.TRUE.equals(approval)) {
            return;
        }

        reservation.setApproval(approval);
        reservationRepository.saveAndFlush(reservation);
    }

    public void updateAttendanceStatus(Long reservationId, Boolean attend) {
        GroupReserProgram reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        // 기존 상태와 동일하면 처리하지 않음
        if (Boolean.TRUE.equals(reservation.getAttend()) == Boolean.TRUE.equals(attend)) {
            return;
        }

        reservation.setAttend(attend);
        reservationRepository.saveAndFlush(reservation);
    }
}
