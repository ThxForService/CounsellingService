package com.thxforservice.reservation.repositories;

import com.thxforservice.reservation.entities.GroupProgram;
import com.thxforservice.reservation.entities.GroupReserProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface GroupReserProgramRepository extends JpaRepository<GroupReserProgram, Long>, QuerydslPredicateExecutor<GroupReserProgram> {
    // 특정 프로그램에 대한 모든 예약을 찾는 메서드
    List<GroupReserProgram> findByProgram(GroupProgram program);

    // 특정 학번과 프로그램에 대한 예약을 찾는 메서드
    List<GroupReserProgram> findByStudentNoAndProgram(Long studentNo, GroupProgram program);

    // 예약 승인 상태와 프로그램을 기반으로 예약 목록을 찾는 메서드
    List<GroupReserProgram> findByApprovalAndProgram(Boolean approval, GroupProgram program);

    // 특정 사용자 이름과 프로그램에 대한 예약을 찾는 메서드
    List<GroupReserProgram> findByUsernameAndProgram(String username, GroupProgram program);
}
