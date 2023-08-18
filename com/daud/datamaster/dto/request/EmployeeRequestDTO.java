package com.daud.datamaster.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeRequestDTO {

    @NotBlank
    private String employeeName;

    @NotBlank
    private String employeePosition;

    @NotNull
    private BigDecimal employeeSalary;
}
