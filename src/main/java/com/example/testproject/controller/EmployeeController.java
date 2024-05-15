package com.example.testproject.controller;

import com.example.testproject.dto.EmployeeDTO;
import com.example.testproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
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
    public ResponseEntity<List<EmployeeDTO>> findByDepartmentName(@PathVariable("nameDepartment") String nameDepartment) {
        return ResponseEntity.ok(employeeService.findByDepartmentName(nameDepartment));
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

    @GetMapping(value = "/export", produces = "application/xml")
    public ResponseEntity<String> getAllEmployees() throws JAXBException {
        return ResponseEntity.ok(employeeService.forExport());
    }

}

