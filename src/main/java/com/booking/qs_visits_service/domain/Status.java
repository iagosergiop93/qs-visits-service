package com.booking.qs_visits_service.domain;

public enum Status {
    REQUESTED("requested"),
    SLOT_UNAVAILABLE("slot_unavailable"),
    SCHEDULED("scheduled"),
    CHECKEDIN("checkedin"),
    INPROGRESS("inprogress"),
    CANCELED("cancelled"),
    COMPLETE("complete");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Status fromValue(String value) {
        for (Status status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + value);
    }
}
