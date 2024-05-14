package com.example.testproject.repository;

import com.example.testproject.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Optional<Department> findByNameDepartmentIgnoreCase  (String nameDepartment);
}
