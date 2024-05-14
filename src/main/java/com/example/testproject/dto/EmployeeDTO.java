package com.example.testproject.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record EmployeeDTO(
        String name,
        String surname,
        String familyName,
        String phone,
        Date birthday,
        Integer departmentId
) {
}
