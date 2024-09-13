package com.thxforservice.counseling.constants;

public enum Status {
    APPLY("예약완료"),
    CANCEL("예약취소"),
    END("상담종료");

    private final String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
