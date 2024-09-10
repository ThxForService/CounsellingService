package com.thxforservice.counselling.repositories;

import com.thxforservice.counselling.entities.Counselling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CounsellingRepository extends JpaRepository<Counselling, Long>, QuerydslPredicateExecutor<Counselling> {

}
