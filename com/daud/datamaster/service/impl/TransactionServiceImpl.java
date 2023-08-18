package com.daud.datamaster.service.impl;

import com.daud.datamaster.dto.TransactionQueryDTO;
import com.daud.datamaster.dto.request.TransactionCreateDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.dto.response.SupplierResponseDTO;
import com.daud.datamaster.dto.response.TransactionListResponseDTO;
import com.daud.datamaster.dto.response.TransactionResponseDTO;
import com.daud.datamaster.entity.Costumer;
import com.daud.datamaster.entity.Transaction;
import com.daud.datamaster.entity.TransactionDetail;
import com.daud.datamaster.exception.ResourceNotFoundException;
import com.daud.datamaster.mapper.TransactionMapper;
import com.daud.datamaster.repository.TransactionRepository;
import com.daud.datamaster.service.CostumerService;
import com.daud.datamaster.service.TransactionService;
import com.daud.datamaster.utill.PaginationUtill;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("transactionService")
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private final CostumerService costumerService;
    @Override
    public void createTransaction(TransactionCreateDTO dto) {
        Transaction transaction = transactionMapper.toEntity(dto);
        transactionRepository.save(transaction);
    }

    @Override
    public TransactionResponseDTO findTransactionById(UUID id) {
        Transaction transaction = transactionRepository.findTransactionById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        return transactionMapper.toDTO(transaction);
    }

    @Override
    public ResultPageResponseDTO<TransactionListResponseDTO> findTransactionsByCostumerName(Integer pages, Integer size, String sortBy, String direction, String costumerName) {
        Sort sort = Sort.by(new Sort.Order(PaginationUtill.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, size, sort);
        Page<TransactionQueryDTO> pageResult  = transactionRepository.findTransactionsByCostumerName(costumerName, pageable);
        List<TransactionListResponseDTO> list = pageResult.stream().map(t -> {
            TransactionListResponseDTO dto = new TransactionListResponseDTO();
            dto.setTransactionId(t.getId());
            dto.setTransactionAmount(t.getAmount());
            dto.setCostumerName(t.getCostumerName());
            dto.setTransactionQuantity(t.getQuantityProduct());
            dto.setCreatedAt(t.getCreatedAt());
            dto.setUpdateAt(t.getUpdateAt());
            return dto;
                }).collect(Collectors.toList());
        ArrayList<TransactionListResponseDTO> dtos = new ArrayList<>(list);
        return PaginationUtill.create(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    @Override
    @Transactional
    public void updateTransaction(UUID transactionId, TransactionCreateDTO dto) {
        Costumer costumer = costumerService.findCostumer(dto.getCostumerId());
        List<TransactionDetail> transactionDetails = findTransactionDetailsByTransactionId(transactionId);

        Transaction transaction = transactionRepository.findTransactionById(transactionId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        int sumQuantity = transactionDetails.stream().mapToInt(TransactionDetail::getQuantity).sum();
        Stream<BigDecimal> bigDecimalStream = transactionDetails.stream().map(TransactionDetail::getAmount);
        BigDecimal sumAmount = bigDecimalStream.reduce(BigDecimal.ZERO, BigDecimal::add);

        transaction.setAmount(sumAmount);
        transaction.setCostumer(dto.getCostumerId()==null? transaction.getCostumer() : costumer);
        transaction.setQuantityProduct(sumQuantity);
        transaction.setCreatedAt(transaction.getCreatedAt());
        transaction.setUpdateAt(LocalDate.now());
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(UUID transactionId) {
        Transaction transaction = transactionRepository.findTransactionById(transactionId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        transactionRepository.delete(transaction);
    }

    @Override
    public Transaction findTransaction(UUID id) {
        return transactionRepository.findTransactionById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
    }

    @Override
    public TransactionResponseDTO construcDTO(Transaction transaction) {
        return transactionMapper.toDTO(transaction);
    }

    @Override
    public List<TransactionDetail> findTransactionDetailsByTransactionId(UUID transactionId) {
        return transactionRepository.findTransactionByTransactionId(transactionId);
    }
}
