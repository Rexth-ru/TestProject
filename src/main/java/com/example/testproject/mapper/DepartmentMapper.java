package com.example.testproject.mapper;

import com.example.testproject.dto.DepartmentDto;
import com.example.testproject.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDto toDepartmentDto(Department department);
}
