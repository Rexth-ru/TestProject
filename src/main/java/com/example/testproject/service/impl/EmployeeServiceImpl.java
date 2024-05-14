package com.example.testproject.service.impl;

import com.example.testproject.dto.EmployeeDTO;
import com.example.testproject.exception.NotFoundException;
import com.example.testproject.mapper.EmployeeMapper;
import com.example.testproject.model.Employee;
import com.example.testproject.repository.DepartmentRepository;
import com.example.testproject.repository.EmployeeRepository;
import com.example.testproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDTO> findAll() {

        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "familyName"))
                .stream()
                .map(employeeMapper::toEmployeeDto)
                .toList();
    }

    @Override
    public List<EmployeeDTO> findByDepartmentName(String departmentName) {
        List<Employee> employees = employeeRepository.findByDepartmentNameDepartmentIgnoreCase(departmentName).orElseThrow(NotFoundException::new);

        return employees.stream().map(employeeMapper::toEmployeeDto).toList();
    }

    @Override
    public EmployeeDTO findByFIO(String familyName, String name, String surname) {
        Employee employee = employeeRepository.findByFamilyNameAndNameAndSurnameIgnoreCase(familyName, name, surname).orElseThrow(NotFoundException::new);
        return employeeMapper.toEmployeeDto(employee);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFamilyName(employeeDTO.familyName());
        employee.setName(employeeDTO.name());
        employee.setSurname(employeeDTO.surname());
        employee.setBirthday(employeeDTO.birthday());
        employee.setPhone(employeeDTO.phone());
        employee.setDepartment(departmentRepository.findById(employeeDTO.departmentId()).orElseThrow(NotFoundException::new));

        return employeeMapper.toEmployeeDto(employeeRepository.save(employee));
    }


    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Integer id) {
        Employee employee = findById(id);
       employee.setFamilyName(employeeDTO.familyName());
       employee.setName(employeeDTO.name());
       employee.setSurname(employeeDTO.surname());
       employee.setBirthday(employeeDTO.birthday());
       employee.setPhone(employeeDTO.phone());
       employee.setDepartment(departmentRepository.findById(employeeDTO.departmentId()).orElseThrow(NotFoundException::new));

        employeeRepository.save(employee);
        return employeeMapper.toEmployeeDto(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.delete(findById(id));
    }

    private Employee findById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
