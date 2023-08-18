package com.daud.datamaster.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SupplierResponseDTO {

    private UUID supplierId;

    private String supplierName;

    private String supplierPhoneNumber;

    private String supplierAddress;

    private LocalDate createdAt;

    private Integer quantityBefore;

    private Integer qtyAdd;

    private LocalDate updateAt;

    private Integer quantity;

}
