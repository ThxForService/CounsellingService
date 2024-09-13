package com.thxforservice.counseling.services;

import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.repositories.GroupProgramRepository;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GroupCounselingDeleteService {

    private final GroupCounselingInfoService infoService;
    private final GroupProgramRepository programRepository;

    // 집단 상담 프로그램 삭제
    public GroupProgram deleteProgram(Long pgmSeq) {
        GroupProgram program = infoService.get(pgmSeq);
        program.setDeletedAt(LocalDateTime.now());

        programRepository.saveAndFlush(program);

        return program;
    }
}
