package com.automecfinder.basic.enums;

public enum ValidationMessages {

    USER_CAN_NOT_BE_NULL("User can not be null"),
    EMAIL_ALREADY_USED("Email already used");

    private String message;

    ValidationMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
