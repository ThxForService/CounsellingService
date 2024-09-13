package com.thxforservice.counseling.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counseling.constants.Status;
import com.thxforservice.counseling.controllers.RequestCounselingApply;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.entities.QCounseling;
import com.thxforservice.counseling.exceptions.CounselingNotFoundException;
import com.thxforservice.counseling.repositories.CounselingRepository;
import com.thxforservice.global.exceptions.BadRequestException;
import com.thxforservice.member.MemberUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CounselingApplyService {
    //
    private final CounselingRepository counselingRepository;
    private final MemberUtil memberUtil;
    private final CounselingStatusService counselingStatusService;

    @Transactional
    public Counseling apply(RequestCounselingApply form) {

//         로그인 확인 및 날짜 유효성 검사
        if (!memberUtil.isLogin() || form.getRDate() == null || form.getRDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("올바른 접근이 아닙니다.");
        }

        // studentNo로 기존 상담이 있는지 확인
        Long studentNo = form.getStudentNo();
        Counseling findCounseling = counselingRepository.findById(studentNo).orElseThrow(CounselingNotFoundException::new);

        // 상담 이력이 있으면 기존 Gid, 없으면 Gid 생성
        String gid = (findCounseling != null) ? findCounseling.getGid() : generateGID(studentNo);

        /* 잊지마라 너의 부족함을 S */
//        if (findCounseling != null) {
//            gid = findCounseling.getGid();
//        } else {
//            gid = generateGID(studentNo);
//        }
        /* 잊지마라 너의 부족함을 E */

        // 사용자 정보 및 상담 세션 검증
        String mobile = form.getMobile();
        if (StringUtils.hasText(mobile)) {
            mobile = mobile.replaceAll("\\D", ""); // 전화번호에서 숫자만 추출
        }

        // 새로운 상담 예약 생성, 수업에선 세터 게터 썻는데 아마 상담프로그램 생성이라 그런가?
        Counseling counseling = Counseling.builder()
                .gid(gid)  // GID 설정
                .username(form.getUsername()) // 내담자 이름
                .studentNo(studentNo) // 학번 설정
                .cCase(form.getCCase()) // 상담 유형
                .cReason(form.getCReason()) // 상담 경위
                .rDate(form.getRDate()) // 예약일
                .rTime(form.getRTime()) // 예약 시간
                .email(form.getEmail()) // 이메일
                .mobile(mobile) // 전화번호
                .build();

        // DB에 저장 및 플러시
        counselingRepository.saveAndFlush(counseling);

        counselingStatusService.change(counseling.getCSeq(), Status.APPLY);

        return counseling;
    }

    // GID 생성 (studentNo를 기반으로 생성)
    private String generateGID(Long studentNo) {
        return UUID.randomUUID().toString();
    }
}