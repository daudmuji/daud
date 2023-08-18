package com.daud.datamaster.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionResponseDTO {

    private UUID transactionId;

    private BigDecimal transactionAmount;

    private CostumerResponseDTO costumer;

    private Integer transactionQuantity;

    private LocalDate createdAt;

    private LocalDate updateAt;
}
