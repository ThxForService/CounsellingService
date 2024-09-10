package com.thxforservice.reservation.services;

import com.thxforservice.member.MemberUtil;
import com.thxforservice.reservation.controllers.RequestGroupReservation;
import com.thxforservice.reservation.entities.GroupProgram;
import com.thxforservice.reservation.entities.GroupReserProgram;
import com.thxforservice.reservation.repositories.GroupProgramRepository;
import com.thxforservice.reservation.repositories.GroupReserProgramRepository;
import com.thxforservice.reservation.repositories.GroupScheduleRepository;
import com.thxforservice.reservation.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GroupReservationApplyService {

    private final GroupReserProgramRepository reservationRepository;
    private final GroupProgramRepository programRepository;
    private final GroupScheduleRepository scheduleRepository;
    private final GroupReservationStatusService statusService;
    private final MemberUtil memberUtil;

    public GroupProgram apply(RequestGroupReservation form) {

        Long programId = form.getProgramId();
        GroupProgram program = programRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program not found"));

        LocalDate reservationDate = form.getReservationDate();
        if (reservationDate != null) {
            scheduleRepository.findByProgramAndDate(program, reservationDate)
                    .orElseThrow(() -> new RuntimeException("Schedule not found for the given date"));
        }

        String username = form.getUsername();
        if (StringUtils.hasText(username)) {
            username = username.trim(); // 이름 공백 제거
        }

        GroupReserProgram reservation = GroupReserProgram.builder()
                .program(program)
                .studentNo(form.getStudentNo())
                .username(username)
                .grade(form.getGrade())
                .department(form.getDepartment())
                .approval(form.getApproval())
                .attend(form.getAttend())
                .build();

        reservationRepository.saveAndFlush(reservation);

        // 예약 접수 상태로 변경 (기본값으로 예약 대기 상태 설정)
        statusService.updateApprovalStatus(reservation.getPgmRegSeq(), Boolean.FALSE);

        return reservation;
    }
}
