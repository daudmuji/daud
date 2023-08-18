package com.daud.datamaster.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProductDetailResDTO {

    private Long productDetailId;

    private ProductResponseDTO product;

    private BrandResponseDTO brand;

    private LocalDate createdAt;

    private Integer quantityBrandBefore;

    private Integer quantityBrand;

    private Integer quantitySum;

    private Integer qtyBefore;

    private Integer qtyAdd;

    private Integer qtyMin;

    private LocalDate updateAt;

    private Integer quantity;

    private BigDecimal price;

    private StorageResponseDTO storage;
}
