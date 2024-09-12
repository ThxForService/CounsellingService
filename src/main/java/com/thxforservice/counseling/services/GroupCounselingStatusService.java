package com.thxforservice.counseling.services;


import com.thxforservice.counseling.constants.ProgramStatus;
import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import com.thxforservice.counseling.repositories.GroupProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupCounselingStatusService {

    private final GroupCounselingInfoService infoService;
    private final GroupProgramRepository programRepository;
    private final GroupCounselingRepository counselingRepository;

    public void change(Long pgmRegSeq, Boolean attend, ProgramStatus status) {

        GroupProgram counseling = infoService.get(pgmRegSeq);
        ProgramStatus prevStatus = counseling.getStatus();
        if (prevStatus == status) { //기존 상태와 동일하면 처리x
            return;
        }

        counseling.setStatus(status);
        counselingRepository.saveAndFlush(counseling);

    }
}
