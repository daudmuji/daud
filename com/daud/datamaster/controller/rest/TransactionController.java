package com.daud.datamaster.controller.rest;

import com.daud.datamaster.dto.request.TransactionCreateDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.dto.response.TransactionListResponseDTO;
import com.daud.datamaster.dto.response.TransactionResponseDTO;
import com.daud.datamaster.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<Void> createNewTransaction(@Valid @RequestBody TransactionCreateDTO dto) {
        transactionService.createTransaction(dto);
        return ResponseEntity.created(URI.create("/transaction")).build();
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionResponseDTO> findTransactionById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(transactionService.findTransactionById(id));
    }

    @GetMapping("/transactions")
    public ResponseEntity<ResultPageResponseDTO<TransactionListResponseDTO>> findTransactions(
           @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
           @RequestParam(name = "size", required = true, defaultValue = "10") Integer size,
           @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
           @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
           @RequestParam(name = "costumerName", required = false) String costumerName
    ) {
        return ResponseEntity.ok(transactionService.findTransactionsByCostumerName(pages, size, sortBy, direction, costumerName));
    }

    @PutMapping("/transaction/{transactionId}")
    public ResponseEntity<Void> updateTransaction(@PathVariable(name = "transactionId") UUID transactionId, @RequestBody TransactionCreateDTO dto) {
        transactionService.updateTransaction(transactionId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/transaction/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable(name = "transactionId") UUID transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok().build();
    }
}
