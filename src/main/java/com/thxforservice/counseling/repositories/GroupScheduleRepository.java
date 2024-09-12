package com.thxforservice.counseling.repositories;

import com.thxforservice.counseling.entities.GroupSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GroupScheduleRepository extends JpaRepository<GroupSchedule, Long>, QuerydslPredicateExecutor<GroupSchedule> {
}
