package com.thxforservice.counseling.services;


import com.thxforservice.counseling.constants.ProgramStatus;
import com.thxforservice.counseling.entities.GroupCounseling;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupCounselingStatusService {

    private final GroupCounselingInfoService infoService;
    private final GroupCounselingRepository counselingRepository;

    public void change(Long pgmRegSeq, Boolean attend, ProgramStatus status) {

        GroupCounseling counseling = infoService.getCounseling(pgmRegSeq);
        ProgramStatus prevStatus = counseling.getProgram().getStatus();
        if (prevStatus == status) { //기존 상태와 동일하면 처리x
            return;
        }

        counseling.getProgram().setStatus(status);
        counselingRepository.saveAndFlush(counseling);

    }
}
