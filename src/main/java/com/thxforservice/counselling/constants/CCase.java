package com.thxforservice.counselling.constants;

public enum CCase {
    ACADEMIC("학업"),
    PSYCHOLOGICAL("심리"),
    EMPLOYMENT("취업"),
    OTHER("기타"); // 기타

    private final String title;

    CCase(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
