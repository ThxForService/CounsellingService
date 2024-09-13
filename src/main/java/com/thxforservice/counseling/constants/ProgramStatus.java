package com.thxforservice.counseling.constants;

public enum ProgramStatus {
    READY("접수중"),
    CONFIRM("접수마감"),
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
