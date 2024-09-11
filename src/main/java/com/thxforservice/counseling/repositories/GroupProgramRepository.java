package com.thxforservice.counseling.repositories;

import com.thxforservice.counseling.entities.GroupProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GroupProgramRepository extends JpaRepository<GroupProgram, Long>, QuerydslPredicateExecutor<GroupProgram> {
}
