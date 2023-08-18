package com.daud.datamaster.service;

import com.daud.datamaster.dto.request.TransactionCreateDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.dto.response.TransactionListResponseDTO;
import com.daud.datamaster.dto.response.TransactionResponseDTO;
import com.daud.datamaster.entity.Transaction;
import com.daud.datamaster.entity.TransactionDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TransactionService {

    public void createTransaction(TransactionCreateDTO dto);

    public TransactionResponseDTO findTransactionById(UUID id);

    public ResultPageResponseDTO<TransactionListResponseDTO> findTransactionsByCostumerName(Integer pages, Integer size, String sortBy,
                                                                                            String direction, String costumerName);

    public void updateTransaction(UUID transactionId, TransactionCreateDTO dto);

    public void deleteTransaction(UUID transactionId);

    public Transaction findTransaction(UUID id);

    public TransactionResponseDTO construcDTO(Transaction transaction);

    public List<TransactionDetail> findTransactionDetailsByTransactionId(UUID transactionId);
}
