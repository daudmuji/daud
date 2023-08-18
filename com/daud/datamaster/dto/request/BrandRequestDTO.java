package com.daud.datamaster.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class BrandRequestDTO {

    @NotBlank
    private String brandName;

    private String brandDescription;

    private UUID supplierId;

    private Integer qtyBefore;

    private Integer qtyAdd;

    private Integer quantity;

}
