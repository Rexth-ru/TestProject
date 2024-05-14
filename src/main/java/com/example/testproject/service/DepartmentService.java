package com.example.testproject.service;

import com.example.testproject.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> findAll();
    DepartmentDto findDepartmentByName(String nameDepartment);
    DepartmentDto addDepartment(DepartmentDto departmentDto);
    DepartmentDto updateDepartment(DepartmentDto departmentDto, Integer id);
    void deleteDepartment(Integer id);
}
