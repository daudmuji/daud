package com.daud.datamaster.controller.rest;

import com.daud.datamaster.dto.request.CostumerRequestDTO;
import com.daud.datamaster.dto.response.CostumerResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.service.CostumerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class CostumerController {

    private final CostumerService costumerService;

    @PostMapping("/costumer")
    public ResponseEntity<Void> createNewCostumer(@Valid @RequestBody CostumerRequestDTO dto)  {
        costumerService.createCostumer(dto);
        return ResponseEntity.created(URI.create("/costumer")).build();
    }

    @GetMapping("/costumer/{id}")
    public ResponseEntity<CostumerResponseDTO> findCostumerById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(costumerService.findCostumerById(id));
    }

    @GetMapping("/costumers")
    public ResponseEntity<ResultPageResponseDTO<CostumerResponseDTO>> findCostumers(
           @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
           @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
           @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
           @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
           @RequestParam(name = "costumerName", required = false) String costumerName
    ) {
        return ResponseEntity.ok(costumerService.findCostumersByName(pages, limit, sortBy, direction, costumerName));
    }

    @PutMapping("/costumer/{costumerId}")
    public ResponseEntity<Void> updateCostumerById(@PathVariable(name = "costumerId")UUID costumerId,
                                                   @RequestBody CostumerRequestDTO dto ) {
        costumerService.updateCostumer(costumerId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("/costumer/{costumerId}")
    public ResponseEntity<Void> deleteCostumerById(@PathVariable(name = "costumerId")UUID costumerId) {
        costumerService.deleteCostumer(costumerId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/costumer/name/{costumerName}")
    public ResponseEntity<CostumerResponseDTO> findCostumerByName(@PathVariable(name = "costumerName")String costumerName) {
        return ResponseEntity.ok(costumerService.findCostumerByName(costumerName));
    }

}
