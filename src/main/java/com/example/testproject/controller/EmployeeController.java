package com.example.testproject.controller;

import com.example.testproject.dto.EmployeeDTO;
import com.example.testproject.dto.EmployeeDTOForXML;
import com.example.testproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
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

//    @GetMapping(value = "/export", produces = MediaType.APPLICATION_XML_VALUE)
//    public ResponseEntity<List<EmployeeDTO>> exportEmployeesToXML() {
//        return ResponseEntity.ok()
//                .body(employeeService.findAll());
//    }
//@GetMapping("/export")
//public ResponseEntity<List<EmployeeDTO>> exportEmployeesToXML() throws JAXBException {
//    List<EmployeeDTO> employees = employeeService.findAll();
//
//    JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeDTO.class);
//    Marshaller marshaller = jaxbContext.createMarshaller();
//    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//    StringWriter sw = new StringWriter();
//    marshaller.marshal(employees, sw);
//
//    HttpHeaders headers = new HttpHeaders();
//    headers.add(HttpHeaders.CONTENT_TYPE, "application/xml");
//
//    return ResponseEntity.ok()
//            .headers(headers)
//            .body(employees);}

    @GetMapping("/export")
    public ResponseEntity<List<EmployeeDTOForXML>> exportEmployeesToXML() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/xml");

        return ResponseEntity.ok()
                .headers(headers)
                .body(employeeService.forExport());
    }
}

