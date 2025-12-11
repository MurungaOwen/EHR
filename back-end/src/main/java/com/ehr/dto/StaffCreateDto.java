package com.ehr.dto;

import com.ehr.models.Staff;

import lombok.Data;

@Data
public class StaffCreateDto {
    private String workId;
    private String firstName;
    private String lastName;
    private String password;
    private Staff.Role role;
}
