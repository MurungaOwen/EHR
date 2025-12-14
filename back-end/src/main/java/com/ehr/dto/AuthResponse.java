package com.ehr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String message;
    private String userType;  // "USER" or "STAFF"
    private String userId;
    private String fullName;
    private String role;

    // Constructor with just message and userType (for register, error, logout)
    public AuthResponse(String message, String userType) {
        this.message = message;
        this.userType = userType;
        this.userId = null;
        this.fullName = null;
        this.role = null;
    }
}