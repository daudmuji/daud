package com.daud.datamaster.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionQueryDTO {

    private UUID id;

    private BigDecimal amount;

    private Integer quantityProduct;

    private LocalDate createdAt;

    private LocalDate updateAt;

    private String costumerName;
}
