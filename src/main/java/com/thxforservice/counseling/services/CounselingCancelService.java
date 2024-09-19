package com.thxforservice.counseling.services;

import com.thxforservice.counseling.constants.Status;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.repositories.CounselingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CounselingCancelService {

    private final CounselingInfoService infoService;
    private final CounselingStatusService statusService;
    private final CounselingRepository repository;

    /**
     * 개인 상담 예약 취소
     *
     * @param cSeq
     * @return
     */
    public Counseling cancel(Long cSeq) {
        Counseling data = infoService.get(cSeq);

        data.setDeletedAt(LocalDateTime.now());

        // 취소 상태 변경
        statusService.change(data.getCSeq(), Status.CANCEL);

        repository.saveAndFlush(data);

        return data;
    }
}

