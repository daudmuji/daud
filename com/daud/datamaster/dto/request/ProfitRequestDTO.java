package com.daud.datamaster.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProfitRequestDTO {

    @NotBlank
    private Integer profitQuantity;

    @NotBlank
    private BigDecimal profitAmount;

}
