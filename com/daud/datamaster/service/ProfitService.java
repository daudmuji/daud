package com.daud.datamaster.service;

import com.daud.datamaster.dto.request.ProfitRequestDTO;
import com.daud.datamaster.dto.response.ProfitResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ProfitService {

    public void createProfit(ProfitRequestDTO dto);

    public ProfitResponseDTO findProfitById(UUID id);

    public ResultPageResponseDTO<ProfitResponseDTO> findProfitsByDateTime(Integer pages, Integer limit, String sortBy,
                                                                          String direction, LocalDate createdAt);

    public List<Transaction> findTransactionsByProfit(UUID profitId);

    public void updateProfit(UUID profitd, ProfitRequestDTO dto);

    public void deleteProfit(UUID profitId);

    public ProfitResponseDTO findProfitByAmount(BigDecimal amountProfit);
}
