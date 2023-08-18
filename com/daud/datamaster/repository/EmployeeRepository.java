package com.daud.datamaster.repository;

import com.daud.datamaster.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    public Optional<Employee> findEmployeeById(UUID id);

    public Optional<Employee> findEmployeeByName(String employeeName);

    public Page<Employee> findEmployeesByNameLikeIgnoreCase(String employeeName, Pageable pageable);
}
