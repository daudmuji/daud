package com.daud.datamaster.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ProductRequestDTO {

    @NotBlank
    private String productName;

    private Integer qtyBefore;

    private Integer quantity;

    private String description;

    @NotBlank
    private Long categoryId;

    private List<Long> productDetailIdList;
}
