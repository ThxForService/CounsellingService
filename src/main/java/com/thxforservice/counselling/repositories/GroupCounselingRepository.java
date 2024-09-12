package com.thxforservice.counselling.repositories;


import com.thxforservice.counselling.entities.GroupProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GroupCounselingRepository extends JpaRepository<GroupProgram, Long>, QuerydslPredicateExecutor<GroupProgram> {
}
