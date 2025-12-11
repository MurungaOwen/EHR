package com.ehr.dto;

import lombok.Data;

@Data
public class StaffLoginRequest {
    private String workId;
    private String password;
}
