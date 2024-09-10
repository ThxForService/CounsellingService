package com.thxforservice.reservation.repositories;

import com.thxforservice.reservation.entities.GroupProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GroupProgramRepository extends JpaRepository<GroupProgram, Long>, QuerydslPredicateExecutor<GroupProgram> {
}
