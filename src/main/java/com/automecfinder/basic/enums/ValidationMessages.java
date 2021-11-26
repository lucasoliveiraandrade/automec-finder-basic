package com.automecfinder.basic.enums;

public enum ValidationMessages {

    USER_CAN_NOT_BE_NULL("User can not be null"),
    EMAIL_ALREADY_USED_STATUS_PENDING("User's activation is pending"),
    EMAIL_ALREADY_USED_STATUS_ACTIVE("User is already active"),
    EMAIL_ALREADY_USED_STATUS_INACTIVE("User is inactive");

    private String message;

    ValidationMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
