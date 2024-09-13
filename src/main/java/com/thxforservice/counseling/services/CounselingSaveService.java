package com.thxforservice.counseling.services;


import com.thxforservice.counseling.controllers.RequestCounselingApply;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.exceptions.CounselingNotFoundException;
import com.thxforservice.counseling.repositories.CounselingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service

/**
 * 메모용입니다.
 *
 * 1조 Counseling = 그룹상담(등록 수정 조회 등)
 *  그러고보니 집단상담에 파일 넣어야하네, 등록하면서 사진도 넣어야지
 *  집단상담 프로그램은 builder로 하지않음, 왜 why? 추가수정삭제를 해야해서. 등록만 할거면 builder, 아니면 세떠 게떠
 *
 *
 *
 *
 */
public class CounselingSaveService {

    private final CounselingRepository repository;
    private final ModelMapper modelMapper;

    public void save(RequestCounselingApply form) {
        Long cSeq = form.getCSeq();
        String mode = Objects.requireNonNullElse(form.getMode(), "write");

        Counseling counseling = null;
        if (mode.equals("update") && cSeq != null) {
            counseling = repository.findById(cSeq).orElseThrow(CounselingNotFoundException::new)
        } else {
            counseling = new Counseling();
            counseling.setGid(form.getGid));
        }
    }

}
