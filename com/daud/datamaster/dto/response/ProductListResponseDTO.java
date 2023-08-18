package com.daud.datamaster.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProductListResponseDTO {

    private Long id;

    private String productName;

    private LocalDate createdAt;

    private Integer quantityBefore;

    private LocalDate updateAt;

    private Integer quantity;

    private String description;

    private String categoryName;
}
