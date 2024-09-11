package com.thxforservice.counselling.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counselling.constants.Status;
import com.thxforservice.counselling.controllers.RequestCounselling;
import com.thxforservice.counselling.entities.Counselling;
import com.thxforservice.counselling.exceptions.CounsellingNotFoundException;
import com.thxforservice.counselling.repositories.CounsellingRepository;
import com.thxforservice.member.MemberUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CounsellingApplyService {

    private final JPAQueryFactory queryFactory; // 목록 조회 패치 조인
    private final CounsellingRepository counsellingRepository;
    private final HttpServletRequest request;
    private final MemberUtil memberUtil;
    private final CounsellingStatusService counsellingStatusService;


    @Transactional
    public Counselling apply(RequestCounselling form) {
        // studentNo로 기존 상담이 있는지 확인
        Long studentNo = form.getStudentNo();
        Counselling findCounselling = counsellingRepository.findById(studentNo).orElseThrow(CounsellingNotFoundException::new);

        // 이미 존재하는 GID인지 확인 (studentNo 기준)
        String gid = form.getGid();

        if (findCounselling != null) {
        // 기존 GID 사용
        gid = findCounselling.getGid();
        } else {
        // GID는 학생별로 고정 또는 생성
        gid = generateGID(studentNo);
        }

        // 사용자 정보 및 상담 세션 검증
        String mobile = form.getMobile();
        if (StringUtils.hasText(mobile)) {
            mobile = mobile.replaceAll("\\D", ""); // 전화번호에서 숫자만 추출
        }

        // 새로운 상담 예약 생성
        Counselling counselling = Counselling.builder()
                .gid(gid)  // GID 설정
                .email(form.getEmail())
                .mobile(mobile)
                .studentNo(studentNo) // 학번 설정
                .rDate(form.getRDate()) // 예약일
                .rTime(form.getRTime()) // 예약 시간
                .cCase(form.getCCase()) // 상담 유형
//                .cCaseDetail(form.getCCaseDetail()) // 기타 상담 유형 (선택사항)
                .agree(true) // 약관 동의 상태
                .build();

        // DB에 저장 및 플러시
        counsellingRepository.saveAndFlush(counselling);

        // 상태 변경
        counsellingStatusService.change(counselling.getCSeq(), Status.APPLY);

        return counselling;
    }

    // GID 생성 로직 (studentNo를 기반으로 생성하거나 기존 값 유지)
    private String generateGID(Long studentNo) {
        // 예: studentNo를 기반으로 고정된 GID 생성
        return UUID.randomUUID().toString();
    }
}
