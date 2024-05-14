package com.example.testproject.controller;

import com.example.testproject.dto.EmployeeDTO;
import com.example.testproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAll() {

        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{nameDepartment}")
    public ResponseEntity<List<EmployeeDTO>> findByDepartmentName(@PathVariable("nameDepartmen") String nameDepartmen) {
        return ResponseEntity.ok(employeeService.findByDepartmentName(nameDepartmen));
    }

    @GetMapping("/{familyName}/{name}/{surname}")
    public ResponseEntity<EmployeeDTO> findByFIO(@PathVariable("familyName") String familyName,
                                                 @PathVariable("name") String name,
                                                 @PathVariable("surname") String surname) {
        return ResponseEntity.ok(employeeService.findByFIO(familyName, name, surname));
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employeeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO,
                                                      @PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.updateEmployee(employeeDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/export/employees")
    public ResponseEntity<List<EmployeeDTO>> exportEmployeesToXML() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/xml");

        return ResponseEntity.ok()
                .headers(headers)
                .body(employeeService.findAll());
    }
}

