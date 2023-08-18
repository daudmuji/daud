package com.daud.datamaster.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SupplierRequestDTO {

    @NotBlank
    private String supplierName;

    @NotBlank
    private String supplierPhoneNumber;

    @NotBlank
    private String supplierAddress;

    private Integer qtyBefore;

    private Integer qtyAdd;

    @NotBlank
    private Integer quantity;

    private BrandRequestDTO brand;

}
