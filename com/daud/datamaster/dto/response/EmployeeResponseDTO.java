package com.daud.datamaster.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EmployeeResponseDTO {

    private UUID employeeId;

    private String employeeName;

    private String employeePosition;

    private BigDecimal employeeSalary;

    private LocalDate createdAt;

    private LocalDate updateAt;
}
