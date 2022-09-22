package com.mushfiqur.hospitalErp.domain;

public enum Slot {
    SLOT_1AM("1 AM - 2 AM"),
    SLOT_2AM("2 AM - 3 AM"),
    SLOT_3AM("3 AM - 4 AM"),
    SLOT_4AM("4 AM - 5 AM"),
    SLOT_5AM("5 AM - 6 AM"),
    SLOT_6AM("6 AM - 7 AM"),
    SLOT_7AM("7 AM - 8 AM"),
    SLOT_8AM("8 AM - 9 AM"),
    SLOT_9AM("9 AM - 10 AM"),
    SLOT_10AM("10 AM - 11 AM"),
    SLOT_11AM("11 AM - 12 PM"),
    SLOT_12PM("12 PM - 1 PM"),
    SLOT_1PM("1 PM - 2 PM"),
    SLOT_2PM("2 PM - 3 PM"),
    SLOT_3PM("3 PM - 4 PM"),
    SLOT_4PM("4 PM - 5 PM"),
    SLOT_5PM("5 PM - 6 PM"),
    SLOT_6PM("6 PM - 7 PM"),
    SLOT_7PM("7 PM - 8 PM"),
    SLOT_8PM("8 PM - 9 PM"),
    SLOT_9PM("9 PM - 10 PM"),
    SLOT_10PM("12 PM - 11 PM"),
    SLOT_11PM("11 PM - 12 AM"),
    SLOT_12AM("12 AM - 1 AM");

    private final String displayName;

    Slot(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
