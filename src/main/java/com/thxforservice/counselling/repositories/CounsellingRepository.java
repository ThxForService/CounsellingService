package com.thxforservice.counselling.repositories;

import com.thxforservice.counselling.entities.Counseling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CounsellingRepository extends JpaRepository<Counseling, Long>, QuerydslPredicateExecutor<Counseling> {

}
