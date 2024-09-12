package com.thxforservice.counselling.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counselling.entities.GroupProgram;
import com.thxforservice.counselling.exceptions.CounselingNotFoundException;
import com.thxforservice.counselling.repositories.GroupCounselingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupCounselingInfoService {

    private final JPAQueryFactory queryFactory;
    private final GroupCounselingRepository counselingRepository;

    /**
     * 예약 상세 정보
     *
     * @param id
     * @return
     */
    public GroupProgram get(Long id) {

        /**
         * [검증]
         * 1. 예약 내역이 존재하는지 체크
         * 2. 조회한 사용자가 admin이 아닐경우 -> 조회한 정보가 로그인한 사용자의 정보인지 검증
         */

        //1. 예약 내역이 존재하는지 체크
        GroupProgram counseling = counselingRepository.findById(id)
                .orElseThrow(CounselingNotFoundException::new);

        //2. 조회한 사용자가 admin이 아닐경우 -> 조회한 정보가 로그인한 사용자의 정보인지 검증



        //추가 정보 처리
        addInfo(counseling);

        return counseling;
    }



    private void addInfo(GroupProgram counseling) {

    }
}
