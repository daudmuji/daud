package com.daud.datamaster.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CategoryResponseDTO {

    private Long categoryId;

    private String categoryName;

    private String description;

    private LocalDate createdAt;

    private LocalDate updateAt;
}
