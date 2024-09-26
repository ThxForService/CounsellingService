package com.thxforservice.counseling.services;

import com.thxforservice.counseling.constants.Status;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.entities.GroupCounseling;
import com.thxforservice.counseling.exceptions.GroupCounselingNotFoundException;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import com.thxforservice.global.exceptions.UnAuthorizedException;
import com.thxforservice.member.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GroupCounselingCancelService {
    private final GroupCounselingRepository repository;
    private final MemberUtil memberUtil;
    private final GroupCounselingStatusService statusService;
    public GroupCounseling cancel(Long pgmRegSeq) {

        /**
         * [검증]
         *
         * 1. 해당 예약이 존재 하는지 조회
         * 2. 해당 예약이 이미 취소상태인지 확인
         * 3. 해당 예약이 본인이 예약 했는지 확인
         */

        //1. 해당 예약이 존재 하는지 조회
        GroupCounseling counseling = repository.findById(pgmRegSeq).orElseThrow(GroupCounselingNotFoundException::new);

        //2. 해당 예약이 이미 취소상태인지 확인
        if(counseling.getDeletedAt() != null) throw new GroupCounselingNotFoundException();

        //3. 해당 예약이 본인이 예약 했는지 확인
        if(counseling.getEmail() != memberUtil.getMember().getEmail()) throw new UnAuthorizedException();

        repository.deleteById(counseling.getPgmRegSeq());
        repository.saveAndFlush(counseling);



        return counseling;
    }

}
