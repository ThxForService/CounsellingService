package com.thxforservice.reservation.constants;

public enum Status {
    APPLY("예약"),
    CANCEL("취소");

    private final String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    }
