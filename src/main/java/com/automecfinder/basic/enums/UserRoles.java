package com.automecfinder.basic.enums;

public enum UserRoles {
    ADMIN("role_admin"),
    USER("role_user");

    private String message;

    UserRoles(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
