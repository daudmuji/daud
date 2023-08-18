package com.daud.datamaster.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQueryDTO {

    private Long id;

    private String productName;

    private LocalDate createdAt;

    private Integer quantityBefore;

    private LocalDate updateAt;

    private Integer quantity;

    private String description;

    private String categoryName;
}
