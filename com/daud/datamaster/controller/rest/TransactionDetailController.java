package com.daud.datamaster.controller.rest;

import com.daud.datamaster.dto.request.TransactionDetailRequestDTO;
import com.daud.datamaster.dto.response.TransactionDetailResponseDTO;
import com.daud.datamaster.service.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class TransactionDetailController {

    private final TransactionDetailService transactionDetailService;

    @PostMapping("/transaction-detail")
    public ResponseEntity<Void> createNew(@RequestBody List<TransactionDetailRequestDTO> dtos) {
        transactionDetailService.createTD(dtos);
        return ResponseEntity.created(URI.create("/transaction-detail")).build();
    }

    @GetMapping("transaction-detail/{id}")
    public ResponseEntity<TransactionDetailResponseDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(transactionDetailService.findById(id));
    }

    @PutMapping("/transaction-detail/{transactionDetailId}")
    public ResponseEntity<Void> updateTD(@PathVariable(name = "transactionDetailId") Long transactionDetailId,
                                         @RequestBody TransactionDetailRequestDTO dto) {
        transactionDetailService.updateTD(transactionDetailId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/transaction-detail/{transactionDetailId}")
    public ResponseEntity<Void> updateTD(@PathVariable(name = "transactionDetailId") Long transactionDetailId) {
        transactionDetailService.deleteTD(transactionDetailId);
        return ResponseEntity.ok().build();
    }
}
