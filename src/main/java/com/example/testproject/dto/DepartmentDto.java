package com.example.testproject.dto;

import lombok.Builder;

@Builder
public record DepartmentDto(
        String nameDepartment,
        String address
) {
}
