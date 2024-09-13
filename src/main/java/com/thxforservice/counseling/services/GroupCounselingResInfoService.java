package com.thxforservice.counseling.services;

import com.thxforservice.counseling.entities.GroupProgram;
import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupCounselingResInfoService {

    private final GroupCounselingInfoService service;
    private final GroupCounselingRepository repository;

    public int count(Long pgmSeq){

        /**
         * [검증]
         * 프로그램이 존재하는지
         */
        GroupProgram program = service.get(pgmSeq);

        return 1;

    }
}
