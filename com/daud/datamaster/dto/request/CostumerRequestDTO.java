package com.daud.datamaster.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CostumerRequestDTO {

    @NotBlank
    private String costumerName;

    private String costumerNumberPhone;

    private String costumerAddress;
}
