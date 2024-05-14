package com.example.testproject.mapper;

import com.example.testproject.dto.EmployeeDTO;
import com.example.testproject.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    @Mapping(target = "departmentId", source = "employee.department.id")
    EmployeeDTO toEmployeeDto(Employee employee);
}
