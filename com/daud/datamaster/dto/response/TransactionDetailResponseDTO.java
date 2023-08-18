package com.daud.datamaster.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class TransactionDetailResponseDTO {

    private Long transactionDetailId;

    private TransactionResponseDTO transaction;

    private ProductDetailResDTO productDetail;

    private Integer transactionDetailQuantity;

    private BigDecimal transactionDetailPrice;

    private BigDecimal transactionDetailAmount;

    private Boolean isActive;

    private LocalDate createdAt;

    private LocalDate updateAt;

}
