package com.thxforservice.counselling.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thxforservice.counselling.constants.CCase;
import com.thxforservice.counselling.constants.Status;
import com.thxforservice.counselling.controllers.RequestCounselingApply;
import com.thxforservice.counselling.entities.Counseling;
import com.thxforservice.counselling.entities.QCounseling;
import com.thxforservice.counselling.exceptions.CounselingNotFoundException;
import com.thxforservice.counselling.repositories.CounsellingRepository;
import com.thxforservice.member.MemberUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CounsellingApplyService {

    private final JPAQueryFactory queryFactory; // 목록 조회 패치 조인
    private final CounsellingRepository counsellingRepository;
    private final HttpServletRequest request;
    private final MemberUtil memberUtil;
    private final CounsellingStatusService counsellingStatusService;
    private final CounsellingInfoService counsellingInfoService;


    @Transactional
    public Counseling apply(RequestCounselingApply form) {
        // studentNo로 기존 상담이 있는지 확인
        Long studentNo = form.getStudentNo();
        Counseling findCounseling = counsellingRepository.findById(studentNo).orElseThrow(CounselingNotFoundException::new);

        // 이미 존재하는 GID인지 확인 (studentNo 기준)
        String gid;

        if (findCounseling != null) {
        // 상담이력이 있으면 기존 Gid 사용
        gid = findCounseling.getGid();
        } else {
        // 없을때 Gid 생성
        gid = generateGID(studentNo);
        }

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
                .rDate(form.getRDate()) // 예약일
                .rTime(form.getRTime()) // 예약 시간
                .cCase(CCase.valueOf(form.getCCase())) // 상담 유형
//                .cCaseDetail(form.getCCaseDetail()) // 기타 상담 유형 (선택사항)
                .agree(form.getAgree()) // 약관 동의 상태
                .build();

        // DB에 저장 및 플러시
        counsellingRepository.saveAndFlush(counseling);

        // 상태 변경
        counsellingStatusService.change(counseling.getCSeq(), Status.APPLY);

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

        QCounseling counselling = QCounseling.counseling;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(counselling.studentNo.eq(studentNo))
                .and(counselling.rDate.eq(rDate))
                .and(counselling.status.eq(Status.APPLY));

        List<Counseling> items = (List<Counseling>) counsellingRepository.findAll(builder);

        // 중복 예약 여부 확인
        return items != null && !items.isEmpty();
    }


    //추가 데이터 처리
//    private void addInfo(Counselling item) {
//        LocalDate startDate = LocalDate.now();
//        Map<LocalDate, boolean[]> availableDates = new HashMap<>();
//
//        int hours = LocalTime.now().getHour();
//        if (hours > 12) { //오후 시간이면 익일 예약 가능
//            startDate = startDate.plusDays(1L);
//            boolean[] amPm = counsellingInfoService.check(startDate, item);
//            if (amPm != null) {
//                availableDates.put(startDate, amPm);
//            }
//        } else { //당일 예약
//            boolean[] time = hours > 8 ? new boolean[] {false, true} : new boolean[]{true, true};
//            boolean[] newTime = counsellingInfoService.check(startDate, item);
//            if (newTime != null) {
//                if (time[0]) time[0] = newTime[0];
//                if (time[1]) time[1] = newTime[1];
//            }
//            if (time[0] || time[1]) {
//                availableDates.put(startDate, time);
//            }
//        }
//
//        LocalDate endDate = startDate.plusMonths(1L).minusDays(1L);
//        Period period = Period.between(startDate, endDate);
//        int days = period.getDays() + 1;
//
//        for (int i = 1 ; i <= days; i++) {
//            /* 이미 예약이 되어 있느 경우 예약 가능일, 시간 블록 제외 처리 */
//            LocalDate rDate = startDate.plusDays(i);
//            boolean[] amPm = counsellingInfoService.check(rDate, item);
//            if (amPm == null) {
//                continue;
//            }
//            availableDates.put(rDate, amPm);
//        }
//
//        item.setAvailableDates(availableDates);
//
//    }

}
