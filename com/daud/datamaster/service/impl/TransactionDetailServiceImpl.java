package com.daud.datamaster.service.impl;

import com.daud.datamaster.dto.request.TransactionDetailRequestDTO;
import com.daud.datamaster.dto.response.TransactionDetailResponseDTO;
import com.daud.datamaster.entity.ProductDetail;
import com.daud.datamaster.entity.Transaction;
import com.daud.datamaster.entity.TransactionDetail;
import com.daud.datamaster.exception.ResourceNotFoundException;
import com.daud.datamaster.repository.ProductDetailRepository;
import com.daud.datamaster.repository.TransactionDetailRepository;
import com.daud.datamaster.service.ProductDetailService;
import com.daud.datamaster.service.TransactionDetailService;
import com.daud.datamaster.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service("transactionDetailService")
@RequiredArgsConstructor
public class TransactionDetailServiceImpl implements TransactionDetailService {

    private final TransactionDetailRepository transactionDetailRepository;

    private final TransactionService transactionService;

    private final ProductDetailService productDetailService;

    private final ProductDetailRepository productDetailRepository;

    @Override
    @Transactional
    public void createTD(List<TransactionDetailRequestDTO> dtos) {
        List<TransactionDetail> transactionDetailList = dtos.stream().map((dto) -> {
            Transaction transaction = transactionService.findTransaction(dto.getTransactionId());
            ProductDetail productDetail = productDetailService.findPD(dto.getProductDetailId());

            if (productDetail.getQuantity() < dto.getTransactionDetailQuantity()) {
                throw new ResourceNotFoundException("invalid quantity");
            }
            productDetail.setQuantity(productDetail.getQuantity() - dto.getTransactionDetailQuantity());
            productDetailRepository.save(productDetail);

            TransactionDetail transactionDetail = new TransactionDetail();
            transactionDetail.setTransaction(transaction);
            transactionDetail.setProductDetail(productDetail);
            transactionDetail.setQuantity(dto.getTransactionDetailQuantity());
            transactionDetail.setPrice(productDetail.getPrice());
            transactionDetail.setAmount(new BigDecimal(dto.getTransactionDetailQuantity()).multiply(dto.getTransactionDetailPrice()));
            transactionDetail.setCreatedAt(LocalDate.now());
            return transactionDetail;

        }).collect(Collectors.toList());
        transactionDetailRepository.saveAll(transactionDetailList);
    }

    @Override
    public TransactionDetailResponseDTO findById(Long id) {
        TransactionDetail transactionDetail = transactionDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        TransactionDetailResponseDTO dto = new TransactionDetailResponseDTO();
        dto.setTransactionDetailId(transactionDetail.getId());
        dto.setTransaction(transactionService.construcDTO(transactionDetail.getTransaction()));
        dto.setProductDetail(productDetailService.construcDTO(transactionDetail.getProductDetail()));
        dto.setTransactionDetailQuantity(transactionDetail.getQuantity());
        dto.setTransactionDetailPrice(transactionDetail.getPrice());
        dto.setTransactionDetailAmount(transactionDetail.getAmount());
        dto.setIsActive(transactionDetail.getIsActive());
        dto.setCreatedAt(transactionDetail.getCreatedAt());
        dto.setUpdateAt(transactionDetail.getUpdateAt());
        return dto;
    }

    @Override
    public void updateTD(Long transactionDetailId, TransactionDetailRequestDTO dto) {
        Transaction transaction = transactionService.findTransaction(dto.getTransactionId());
        ProductDetail productDetail = productDetailService.findPD(dto.getProductDetailId());

        TransactionDetail transactionDetail = transactionDetailRepository.findById(transactionDetailId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        transactionDetail.setTransaction(dto.getTransactionId()==null? transactionDetail.getTransaction() : transaction);
        transactionDetail.setProductDetail(dto.getProductDetailId()==null? transactionDetail.getProductDetail() : productDetail);
        transactionDetail.setQuantity(dto.getTransactionDetailQuantity()==null? transactionDetail.getQuantity() : dto.getTransactionDetailQuantity());
        transactionDetail.setPrice(dto.getTransactionDetailPrice()==null? transactionDetail.getPrice() : dto.getTransactionDetailPrice());

        if (dto.getTransactionDetailQuantity()==null || dto.getTransactionDetailPrice()==null) {
            transactionDetail.setAmount(transactionDetail.getAmount());
        }

        transactionDetail.setAmount(new BigDecimal(dto.getTransactionDetailQuantity()).multiply(dto.getTransactionDetailPrice()));
        transactionDetail.setIsActive(dto.getIsActive()==null? transactionDetail.getIsActive() : dto.getIsActive());
        transactionDetail.setCreatedAt(transactionDetail.getCreatedAt());
        transactionDetail.setUpdateAt(LocalDate.now());
    }

    @Override
    public void deleteTD(Long transactionDetailId) {
        TransactionDetail transactionDetail = transactionDetailRepository.findById(transactionDetailId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        transactionDetailRepository.delete(transactionDetail);
    }

}
