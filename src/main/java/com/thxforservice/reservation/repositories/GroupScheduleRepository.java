package com.thxforservice.reservation.repositories;

import com.thxforservice.reservation.entities.GroupProgram;
import com.thxforservice.reservation.entities.GroupSchedule;
import com.thxforservice.reservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDate;
import java.util.List;

public interface GroupScheduleRepository extends JpaRepository<GroupSchedule, Long>, QuerydslPredicateExecutor<GroupSchedule> {
    List<GroupSchedule> findByProgramAndDate(GroupProgram program, LocalDate date);
}
