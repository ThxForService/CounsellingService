package com.thxforservice.counseling.constants;



public enum CReason {
    VOLUNTARY("자발적"),
    RECOMMENDED("권유");

    private final String title;

    CReason(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}