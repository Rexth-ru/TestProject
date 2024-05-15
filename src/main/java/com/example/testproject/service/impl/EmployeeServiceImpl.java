package com.example.testproject.service.impl;

import com.example.testproject.dto.EmployeeDTO;
import com.example.testproject.dto.EmployeeDTOForXML;
import com.example.testproject.dto.EmployeeForXML;
import com.example.testproject.exception.NotFoundException;
import com.example.testproject.mapper.EmployeeMapper;
import com.example.testproject.model.Department;
import com.example.testproject.model.Employee;
import com.example.testproject.repository.DepartmentRepository;
import com.example.testproject.repository.EmployeeRepository;
import com.example.testproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
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
        List<EmployeeDTO> employeeDTOList = getAllBySort();
        if (employeeDTOList.isEmpty()) throw new NotFoundException("Сисок пуст");
        return employeeDTOList;
    }

    @Override
    public String forExport() throws JAXBException {
        List<EmployeeForXML> employeeDTOList = getAllBySort().stream().map(employeeMapper::toEmployeeForXML).toList();
        if (employeeDTOList.isEmpty()) throw new NotFoundException("Сисок пуст");
        JAXBContext context = JAXBContext.newInstance(EmployeeDTOForXML.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(new EmployeeDTOForXML(employeeDTOList), writer);

        return writer.toString();
    }

    @Override
    public List<EmployeeDTO> findByDepartmentName(String departmentName) {
        Department department = departmentRepository.findByNameDepartmentIgnoreCase(departmentName).orElseThrow(NotFoundException::new);
        List<Employee> employees = employeeRepository.findByDepartment(department).orElseThrow(NotFoundException::new);

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

    private List<EmployeeDTO> getAllBySort() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "familyName"))
                .stream()
                .map(employeeMapper::toEmployeeDto)
                .toList();
    }
}
