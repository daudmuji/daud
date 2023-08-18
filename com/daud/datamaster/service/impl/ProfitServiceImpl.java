package com.daud.datamaster.service.impl;

import com.daud.datamaster.dto.request.ProfitRequestDTO;
import com.daud.datamaster.dto.response.ProfitResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.entity.Profit;
import com.daud.datamaster.entity.Transaction;
import com.daud.datamaster.exception.ResourceNotFoundException;
import com.daud.datamaster.mapper.ProfitMapper;
import com.daud.datamaster.repository.ProfitRepository;
import com.daud.datamaster.service.ProfitService;
import com.daud.datamaster.utill.PaginationUtill;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("profitService")
@RequiredArgsConstructor
public class ProfitServiceImpl implements ProfitService {

    private final ProfitRepository profitRepository;

    private final ProfitMapper profitMapper;


    @Override
    public void createProfit(ProfitRequestDTO dto) {
        Profit profit = profitMapper.toEntity(dto);
        profitRepository.save(profit);
    }

    @Override
    public ProfitResponseDTO findProfitById(UUID id) {
        Profit profit = profitRepository.findProfitById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        return profitMapper.toDTO(profit);
    }

    @Override
    public ResultPageResponseDTO<ProfitResponseDTO> findProfitsByDateTime(Integer pages, Integer limit, String sortBy, String direction, LocalDate createdAt) {
        Sort sort = Sort.by(new Sort.Order(PaginationUtill.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Profit> pageResult = profitRepository.findProfitsByCreatedAt(createdAt, pageable);
        List<ProfitResponseDTO> list = pageResult.stream().map((p) -> {
            ProfitResponseDTO dto = new ProfitResponseDTO();
            dto.setProfitId(p.getId());
            dto.setCreatedAt(p.getCreatedAt());
            dto.setUpdateAt(p.getUpdateAt());
            dto.setProfitQuantity(p.getQuantity());
            dto.setProfitAmount(p.getAmount());
            return dto;
        }).collect(Collectors.toList());
        ArrayList<ProfitResponseDTO> dtos = new ArrayList<>(list);
        return PaginationUtill.create(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    @Override
    public List<Transaction> findTransactionsByProfit(UUID profitId) {
        return profitRepository.findTransactionsByProfitId(profitId);
    }

    @Override
    public void updateProfit(UUID profitId, ProfitRequestDTO dto) {
        Stream<Transaction> transaction = findTransactionsByProfit(profitId).stream();
        int sumQuantity = transaction.mapToInt(Transaction::getQuantityProduct).sum();
        BigDecimal sumAmount = transaction.map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);


        Profit profit = profitRepository.findProfitById(profitId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        profit.setCreatedAt(profit.getCreatedAt());
        profit.setUpdateAt(LocalDate.now());
        profit.setQuantity(sumQuantity);
        profit.setAmount(sumAmount);
        profitRepository.save(profit);
    }

    @Override
    public void deleteProfit(UUID profitId) {
        Profit profit = profitRepository.findProfitById(profitId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        profitRepository.delete(profit);
    }

    @Override
    public ProfitResponseDTO findProfitByAmount(BigDecimal amountProfit) {
        Profit profit = profitRepository.findProfitByAmount(amountProfit).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        return profitMapper.toDTO(profit);
    }
}
