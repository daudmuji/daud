package com.daud.datamaster.service.impl;

import com.daud.datamaster.dto.request.EmployeeRequestDTO;
import com.daud.datamaster.dto.response.EmployeeResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.dto.response.SupplierResponseDTO;
import com.daud.datamaster.entity.Employee;
import com.daud.datamaster.exception.ResourceNotFoundException;
import com.daud.datamaster.mapper.EmployeeMapper;
import com.daud.datamaster.repository.EmployeeRepository;
import com.daud.datamaster.service.EmployeeService;
import com.daud.datamaster.utill.PaginationUtill;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("employeeService")
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    @Override
    public void createEmployee(EmployeeRequestDTO dto) {
        Employee employee = employeeMapper.toEntity(dto);
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeResponseDTO findEmployeeById(UUID id) {
        Employee employee = employeeRepository.findEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        return employeeMapper.toDTO(employee);
    }

    @Override
    public ResultPageResponseDTO<EmployeeResponseDTO> findEmployeesByName(Integer pages, Integer limit, String sortBy, String direction, String employeeName) {
        employeeName = StringUtils.isBlank(employeeName)?"%":"%"+employeeName+"%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtill.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Employee> pageResult = employeeRepository.findEmployeesByNameLikeIgnoreCase(employeeName, pageable);
        List<EmployeeResponseDTO> list = pageResult.stream().map(e -> {
            EmployeeResponseDTO dto = new EmployeeResponseDTO();
            dto.setEmployeeId(e.getId());
            dto.setEmployeeName(e.getName());
            dto.setEmployeePosition(e.getPosition());
            dto.setEmployeeSalary(e.getSalary());
            dto.setCreatedAt(e.getCreatedAt());
            dto.setUpdateAt(e.getUpdateAt());
            return dto;
        }).collect(Collectors.toList());
        ArrayList<EmployeeResponseDTO> dtos = new ArrayList<>(list);
        return PaginationUtill.create(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    @Override
    public void updateEmployeeById(UUID employeeId, EmployeeRequestDTO dto) {
        Employee employee = employeeRepository.findEmployeeById(employeeId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        employeeMapper.updateEntity(dto, employee);
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(UUID employeeId) {
        Employee employee = employeeRepository.findEmployeeById(employeeId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeResponseDTO findEmployeeByName(String employeeName) {
        Employee employee = employeeRepository.findEmployeeByName(employeeName).orElseThrow(() -> new ResourceNotFoundException("invalid name"));
        return employeeMapper.toDTO(employee);
    }


}
