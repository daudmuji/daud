package com.daud.datamaster.service;

import com.daud.datamaster.dto.request.TransactionDetailRequestDTO;
import com.daud.datamaster.dto.response.TransactionDetailResponseDTO;
import com.daud.datamaster.entity.TransactionDetail;

import java.util.List;

public interface TransactionDetailService {

    public void createTD(List<TransactionDetailRequestDTO> dtos);

    public TransactionDetailResponseDTO findById(Long id);

    public void updateTD(Long transactionDetailId, TransactionDetailRequestDTO dto);

    public void deleteTD(Long transactionDetailId);

}
