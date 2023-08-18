package com.daud.datamaster.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class TransactionDetailRequestDTO {

    private UUID transactionId;

    private Long productDetailId;

    private Integer transactionDetailQuantity;

    private BigDecimal transactionDetailPrice;

    private BigDecimal transactionDetailAmount;

    private Boolean isActive;

    private LocalDateTime dateTimeBefore;

    private LocalDateTime dateTimeUpdate;

}
