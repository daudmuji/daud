package com.daud.datamaster.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProfitResponseDTO {

    private UUID profitId;

    private LocalDate createdAt;

    private LocalDate updateAt;

    private Integer profitQuantity;

    private BigDecimal profitAmount;
}
