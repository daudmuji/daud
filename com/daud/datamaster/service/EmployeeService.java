package com.daud.datamaster.service;

import com.daud.datamaster.dto.request.EmployeeRequestDTO;
import com.daud.datamaster.dto.response.EmployeeResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;

import java.util.UUID;

public interface EmployeeService {

    public void createEmployee(EmployeeRequestDTO dto);

    public EmployeeResponseDTO findEmployeeById(UUID id);

    public ResultPageResponseDTO<EmployeeResponseDTO> findEmployeesByName(Integer pages, Integer limit, String sortBy,
                                                                         String direction, String employeeName);

    public void updateEmployeeById(UUID employeeId, EmployeeRequestDTO dto);

    public void deleteEmployeeById(UUID employeeId);

    public EmployeeResponseDTO findEmployeeByName(String employeeName);

}
