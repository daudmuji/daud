package com.daud.datamaster.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BrandResponseDTO {

    private Long brandId;

    private String brandName;

    private String brandDescription;

    private SupplierResponseDTO supplier;

    private LocalDate createdAt;

    private Integer quantityBefore;

    private Integer qtyAdd;

    private LocalDate updateAt;

    private Integer quantity;

}
