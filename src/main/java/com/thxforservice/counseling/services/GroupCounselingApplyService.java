package com.thxforservice.counseling.services;

import com.thxforservice.counseling.controllers.RequestGroupCounselingApply;
import com.thxforservice.counseling.entities.GroupCounseling;
import com.thxforservice.counseling.repositories.GroupProgramRepository;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupCounselingApplyService { //신청하는 거 + 신청목록 조회 + 신청 상세정보 조회

    private final GroupProgramRepository programRepository;
    private final GroupCounselingRepository counselingRepository;

    public GroupCounseling apply(RequestGroupCounselingApply form) {

        return null;
    }
}
