package com.example.testproject.repository;

import com.example.testproject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
Optional<List<Employee>> findByDepartmentNameDepartmentIgnoreCase(String nameDepartment);
Optional<Employee> findByFamilyNameAndNameAndSurnameIgnoreCase(String familyName, String name, String surname);
}
