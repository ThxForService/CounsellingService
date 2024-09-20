package com.thxforservice.counseling.services;

import com.thxforservice.counseling.repositories.GroupCounselingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupCounselingResInfoService {


    //현재 취소상태가 아닌 정상적인 예약 갯수.
    public int count(Long pgmSeq){

        /**
         * [검증]
         * 프로그램이 존재하는지
         */

        /*
        GroupProgram program = service.get(pgmSeq);
        List<GroupCounseling> counselings =  repository.findByProgram_PgmSeq(program.getPgmSeq());
        int count = (int)counselings.stream()
                        .filter(couseling -> couseling.getDeletedAt() == null)
                        .count();
         */



        return 1;

    }
}
