package com.daud.datamaster.controller.rest;

import com.daud.datamaster.dto.request.ProfitRequestDTO;
import com.daud.datamaster.dto.response.ProfitResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.service.ProfitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class ProfitController {

    private final ProfitService profitService;

    @PostMapping("/profit")
    public ResponseEntity<Void> createNewProfit(@Valid @RequestBody ProfitRequestDTO dto) {
        profitService.createProfit(dto);
        return ResponseEntity.created(URI.create("/profit")).build();
    }

    @GetMapping("/profit/{id}")
    public ResponseEntity<ProfitResponseDTO> findProfitById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(profitService.findProfitById(id));
    }

    @GetMapping("/profits")
    public ResponseEntity<ResultPageResponseDTO<ProfitResponseDTO>> findProfits(
           @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
           @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
           @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
           @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
           @RequestParam(name = "createdAt", required = false) String createdAt
    ) {
        LocalDate localDate = LocalDate.parse(createdAt);
        return ResponseEntity.ok(profitService.findProfitsByDateTime(pages, limit, sortBy, direction, localDate));
    }

    @PutMapping("/profit/{profitId}")
    public ResponseEntity<Void> updateProfitById(@PathVariable(name = "profitId") UUID profitId,
                                                 @RequestBody ProfitRequestDTO dto) {
        profitService.updateProfit(profitId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/profit/{profitId}")
    public ResponseEntity<Void> deleteProfitById(@PathVariable(name = "profitId") UUID profitId) {
        profitService.deleteProfit(profitId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("profit/{profitAmount}")
    public ResponseEntity<ProfitResponseDTO> findProfitByAmount(@PathVariable(name = "profitAmount") BigDecimal profitAmount) {
        return ResponseEntity.ok(profitService.findProfitByAmount(profitAmount));
    }
}
