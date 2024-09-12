package com.thxforservice.counselling.services;

import com.thxforservice.counselling.entities.GroupProgram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupCounselingRegInfoService {


    /**
     * 학생의 그룹상담 조회(단일)
     * @param id
     * @return
     */
    public GroupProgram get(Long id) {
        GroupProgram groupProgram = new GroupProgram();

        return groupProgram;

    }


}
