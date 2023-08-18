package com.daud.datamaster.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class TransactionCreateDTO {

    @NotBlank
    private BigDecimal transactionAmount;

    @NotBlank
    private UUID costumerId;

    @NotBlank
    private Integer transactionQuantity;

    private List<TransactionDetailRequestDTO> transactionDetails;

}
