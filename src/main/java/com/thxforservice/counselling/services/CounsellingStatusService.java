package com.thxforservice.counselling.services;

import com.thxforservice.counselling.constants.Status;
import com.thxforservice.counselling.entities.Counseling;
import com.thxforservice.counselling.repositories.CounsellingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CounsellingStatusService {
    private final CounsellingRepository counsellingRepository;
    private final CounsellingInfoService counsellingInfoService;

    public void change(Long Cseq, Status status) {
        // Counselling 엔티티 가져오기
        Counseling counseling = counsellingInfoService.get(Cseq);
        Status prevStatus = counseling.getStatus();
//        System.out.println("Previous Status: " + prevStatus);

        if (prevStatus != status) { // 기존 상태와 동일하지 않은 경우에만 상태 변경
            counseling.setStatus(status);
            counsellingRepository.saveAndFlush(counseling);
//            System.out.println("상태 변경값: " + status);
        }
    }
}

