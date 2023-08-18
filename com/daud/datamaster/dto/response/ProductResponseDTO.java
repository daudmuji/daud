package com.daud.datamaster.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductResponseDTO {

    private Long productId;

    private String productName;

    private LocalDate createdAt;

    private Integer qtyBefore;

    private LocalDate updateAt;

    private Integer quantity;

    private String description;

    private CategoryResponseDTO category;

    //private List<ProductDetailResponseDTO> productDetailList;

}
