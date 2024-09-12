package com.thxforservice.counseling.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counseling.entities.GroupCounseling;
import com.thxforservice.counseling.exceptions.CounselingNotFoundException;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
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
    public GroupCounseling get(Long id) {
        GroupCounseling counseling = counselingRepository.findById(id)
                .orElseThrow(CounselingNotFoundException::new);

        //추가 정보 처리
        addInfo(counseling);

        return counseling;
    }



    private void addInfo(GroupCounseling counseling) {

    }
}
