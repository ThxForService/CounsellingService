package com.thxforservice.counseling.services;

import com.thxforservice.counseling.constants.Status;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.repositories.CounselingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CounselingStatusService {

//    private final CounselingInfoService infoService;
    private final CounselingRepository repository;

//    public void change(Long seq, Status status) {
//        Counseling counseling = infoService.get(seq);
//        Status prevStatus = counseling.getStatus();
//
//        if (prevStatus != status) { //기존 상태와 동일하면 처리 x
//            counseling.setStatus(status);
//            repository.saveAndFlush(counseling);
//            return;
//        }
//    }
//
//    public void change(List<Long> seqs, Status status) {
//        seqs.forEach(seq -> change(seq, status));
//    }
}
