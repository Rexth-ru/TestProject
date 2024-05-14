package com.example.testproject.controller;

import com.example.testproject.dto.DepartmentDto;
import com.example.testproject.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> findAll() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/{nameDepartment}")
    public ResponseEntity<DepartmentDto> findDepartmentByName(@PathVariable("nameDepartmen") String nameDepartmen){
        return ResponseEntity.ok(departmentService.findDepartmentByName(nameDepartmen));
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
       return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.addDepartment(departmentDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto,
                                                          @PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.updateDepartment(departmentDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Integer id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok().build();
    }
}
