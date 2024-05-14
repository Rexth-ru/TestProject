package com.example.testproject.service;

import com.example.testproject.dto.DepartmentDto;
import com.example.testproject.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> findAll();
    List<EmployeeDTO> findByDepartmentName(String departmentName);
    EmployeeDTO findByFIO(String familyName, String name, String surname);
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Integer id);
    void deleteEmployee(Integer id);
}
