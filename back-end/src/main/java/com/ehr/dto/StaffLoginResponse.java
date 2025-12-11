package com.ehr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StaffLoginResponse {
    private String message;
    private String sessionId;
}
