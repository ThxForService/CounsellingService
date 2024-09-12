package com.thxforservice.counseling.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counseling.constants.Status;
import com.thxforservice.counseling.controllers.RequestCounselingApply;
import com.thxforservice.counseling.entities.Counseling;
import com.thxforservice.counseling.entities.QCounseling;
import com.thxforservice.counseling.exceptions.CounselingNotFoundException;
import com.thxforservice.counseling.repositories.CounselingRepository;
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
    private final CounselingInfoService CounselingInfoService;


    @Transactional
    public Counseling apply(RequestCounselingApply form) {
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

        // 새로운 상담 예약 생성
        Counseling counseling = Counseling.builder()
                .gid(gid)  // GID 설정
                .email(form.getEmail())
                .mobile(mobile)
                .studentNo(studentNo) // 학번 설정
                .cCase(form.getCCase()) // 상담 유형
                .cReason(form.getCReason()) // 상담 경위
                .username(form.getUsername()) // 내담자 이름
                .rDate(form.getRDate()) // 예약일
                .rTime(form.getRTime()) // 예약 시간
                .build();

        // DB에 저장 및 플러시
        counselingRepository.saveAndFlush(counseling);


        return counseling;
    }

    // GID 생성 (studentNo를 기반으로 생성)
    private String generateGID(Long studentNo) {
        return UUID.randomUUID().toString();
    }

    // 중복 예약 확인 처리
    public boolean check(LocalDate rDate, Long studentNo) {
        // 로그인 확인 및 날짜 유효성 검사
        if (!memberUtil.isLogin() || rDate == null) {
            return false;
        }

        QCounseling Counseling = QCounseling.counseling;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(Counseling.studentNo.eq(studentNo))
                .and(Counseling.rDate.eq(rDate))
                .and(Counseling.status.eq(Status.APPLY));

        List<Counseling> items = (List<Counseling>) counselingRepository.findAll(builder);

        // 중복 예약 여부 확인
        return items != null && !items.isEmpty();
    }
}