package com.thxforservice.counseling.constants;

public enum Status {
    APPLY("예약접수"),
    CONFIRM("예약확인"),
    CANCEL("예약취소");

    private final String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
