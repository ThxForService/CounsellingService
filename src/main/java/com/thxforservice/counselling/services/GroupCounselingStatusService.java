package com.thxforservice.counselling.services;


import com.thxforservice.counselling.constants.Status;
import com.thxforservice.counselling.entities.GroupCounseling;
import com.thxforservice.counselling.entities.GroupProgram;
import com.thxforservice.counselling.repositories.GroupCounselingRepository;
import com.thxforservice.counselling.repositories.GroupProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupCounselingStatusService {

    private final GroupCounselingInfoService infoService;
    private final GroupProgramRepository repository;

    public void change(Long pgmRegSeq, Boolean attend, Status status) {
        GroupCounseling program = infoService.get(pgmRegSeq);
        boolean updated = false;
        Status prevStatus = program.getStatus();

        if (prevStatus == status) { // 기존 상태와 동일하면 처리 X
            return;
        }

        if (attend != null && !attend.equals(program.getAttend())) {
            program.setAttend(attend);
            updated = true;
        }

        if (updated) {
            repository.saveAndFlush(program);
        }
    }
}
