package com.daud.datamaster.mapper;

import com.daud.datamaster.dto.request.EmployeeRequestDTO;
import com.daud.datamaster.dto.response.EmployeeResponseDTO;
import com.daud.datamaster.entity.Employee;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequestDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getEmployeeName());
        employee.setPosition(dto.getEmployeePosition());
        employee.setSalary(dto.getEmployeeSalary());
        employee.setCreatedAt(LocalDate.now());
        return employee;
    }

    public EmployeeResponseDTO toDTO(Employee employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setEmployeeId(employee.getId());
        dto.setEmployeeName(employee.getName());
        dto.setEmployeePosition(employee.getPosition());
        dto.setEmployeeSalary(employee.getSalary());
        dto.setCreatedAt(employee.getCreatedAt());
        dto.setUpdateAt(employee.getUpdateAt());
        return dto;
    }

    public void updateEntity(EmployeeRequestDTO dto, Employee employee) {
        employee.setName(dto.getEmployeeName()==null? employee.getName() : dto.getEmployeeName());
        employee.setPosition(dto.getEmployeePosition()==null? employee.getPosition() : dto.getEmployeePosition());
        employee.setSalary(dto.getEmployeeSalary()==null? employee.getSalary() : dto.getEmployeeSalary());
        employee.setCreatedAt(employee.getCreatedAt());
        employee.setUpdateAt(LocalDate.now());
    }
}
