package com.thxforservice.counseling.repositories;

import com.thxforservice.counseling.entities.GroupCounseling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface GroupCounselingRepository extends JpaRepository<GroupCounseling, Long>, QuerydslPredicateExecutor<GroupCounseling> {

    List<GroupCounseling> findByProgram_PgmSeq(Long pgmSeq);
}
