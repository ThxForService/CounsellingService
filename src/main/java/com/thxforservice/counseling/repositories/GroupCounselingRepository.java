package com.thxforservice.counseling.repositories;

import com.thxforservice.counseling.entities.GroupCounseling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GroupCounselingRepository extends JpaRepository<GroupCounseling, Long>, QuerydslPredicateExecutor<GroupCounseling> {

}
