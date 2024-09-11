package com.thxforservice.counselling.repositories;

import com.thxforservice.counselling.entities.GroupProgram;
import com.thxforservice.counselling.entities.GroupSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDate;
import java.util.List;

public interface GroupScheduleRepository extends JpaRepository<GroupSchedule, Long>, QuerydslPredicateExecutor<GroupSchedule> {
}