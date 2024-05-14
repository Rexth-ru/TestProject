package com.example.testproject.service.impl;

import com.example.testproject.dto.DepartmentDto;
import com.example.testproject.exception.NotFoundException;
import com.example.testproject.mapper.DepartmentMapper;
import com.example.testproject.model.Department;
import com.example.testproject.repository.DepartmentRepository;
import com.example.testproject.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll(Sort.by(Sort.Direction.ASC, "nameDepartment"))
                .stream()
                .map(departmentMapper::toDepartmentDto)
                .toList();
    }

    @Override
    public DepartmentDto findDepartmentByName(String nameDepartment) {
        Department departmentDto = departmentRepository.findDepartmentByNameDepartment(nameDepartment).orElseThrow(NotFoundException::new);
        return departmentMapper.toDepartmentDto(departmentDto);
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setNameDepartment(departmentDto.nameDepartment());
        department.setAddress(departmentDto.address());
        return departmentMapper.toDepartmentDto(departmentRepository.save(department));
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto, Integer id) {
        Department department = findById(id);
        department.setNameDepartment(departmentDto.nameDepartment());
        department.setAddress(departmentDto.address());
        departmentRepository.save(department);

        return departmentMapper.toDepartmentDto(department);
    }

    @Override
    public void deleteDepartment(Integer id) {
        departmentRepository.delete(findById(id));
    }

    private Department findById(Integer id) {
        return departmentRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
