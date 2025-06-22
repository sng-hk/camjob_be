package com.example.camjob.entity.role;

public enum UserRole {
    ROLE_USER,
    ROLE_MD,
    ROLE_ADMIN;

    public String getAuthority() {
        return this.name();
    }
}
