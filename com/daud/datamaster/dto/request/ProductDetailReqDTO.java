package com.daud.datamaster.dto.request;

import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductDetailReqDTO {

    private Long productDetailId;

    private Long productId;

    private Long brandId;

    private Integer quantityBrandBefore;

    private Integer quantityBrand;

    private Integer qtyBefore;

    private Integer qtyAdd;

    private Integer qtyMin;

    private Integer quantity;

    private BigDecimal price;

    private UUID storageId;
}
