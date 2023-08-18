package com.daud.datamaster.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDTO {

    @NotBlank
    private String categoryName;

    private String categoryDescription;
}
