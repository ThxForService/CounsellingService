package com.thxforservice.counseling.repositories;

import com.thxforservice.counseling.entities.Counseling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CounselingRepository extends JpaRepository<Counseling, Long>, QuerydslPredicateExecutor<Counseling> {
}
