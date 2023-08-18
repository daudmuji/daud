package com.daud.datamaster.mapper;

import com.daud.datamaster.dto.request.TransactionCreateDTO;
import com.daud.datamaster.dto.response.TransactionResponseDTO;
import com.daud.datamaster.entity.Costumer;
import com.daud.datamaster.entity.Transaction;
import com.daud.datamaster.service.CostumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    private final CostumerService costumerService;

    public Transaction toEntity(TransactionCreateDTO dto) {


        Costumer costumer = costumerService.findCostumer(dto.getCostumerId());
        Transaction transaction = new Transaction();
        transaction.setCostumer(costumer);
        transaction.setCreatedAt(LocalDate.now());
        return transaction;
    }

    public TransactionResponseDTO toDTO(Transaction transaction) {
        TransactionResponseDTO dto = new TransactionResponseDTO();
        dto.setTransactionAmount(transaction.getAmount());
        dto.setCostumer(costumerService.construcDTO(transaction.getCostumer()));
        dto.setTransactionQuantity(transaction.getQuantityProduct());
        dto.setCreatedAt(transaction.getCreatedAt());
        dto.setUpdateAt(transaction.getUpdateAt());
        return dto;
    }

}
