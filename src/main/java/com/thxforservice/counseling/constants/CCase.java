package com.thxforservice.counseling.constants;

public enum CCase {
    FAMILY("가족"),
    ACADEMIC("학업/진로"),
    RELATIONSHIPS("대인관계"),
    EMOTIONAL("심리정서"),
    BEHAVIOR("생활습관 및 행동문제"),
    ROMANCE_SEX("연애와 성"),
    LIFE_VALUES("삶과 가치"),
    PERSONALITY("성격"),
    OTHER("기타");  // 기타

    private final String title;

    CCase(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
