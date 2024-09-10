package com.thxforservice.counselling.constants;

public enum ProgramStatus {
    READY("접수중"),
    START("진행중"),
    END("상담종료");

    private final String title;

    ProgramStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
