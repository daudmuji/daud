package com.daud.datamaster.controller.rest;

import com.daud.datamaster.dto.request.SupplierRequestDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.dto.response.SupplierResponseDTO;
import com.daud.datamaster.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping("/supplier")
    public ResponseEntity<Void> createNewSupplier(@RequestBody SupplierRequestDTO dto) {
        supplierService.createSupplier(dto);
        return ResponseEntity.created(URI.create("/supplier")).build();
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<SupplierResponseDTO> findSupplierById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(supplierService.findSupplierById(id));
    }

    @GetMapping("/suppliers")
    public ResponseEntity<ResultPageResponseDTO<SupplierResponseDTO>> findSuplliers(
           @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
           @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
           @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
           @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
           @RequestParam(name = "brandName", required = false) String supplierName
    ) {
        return ResponseEntity.ok(supplierService.findSuppliersByName(pages, limit, sortBy, direction, supplierName));
    }

    @PutMapping("/supplier/{supplierId}")
    public ResponseEntity<Void> updateSupplierById(@PathVariable(name = "supplierId") UUID supplierId,
                                                   @RequestBody SupplierRequestDTO dto) {
        supplierService.updateSupplier(supplierId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/supplier/{supplierId}")
    public ResponseEntity<Void> deleteSupplierById(@PathVariable(name = "supplierId") UUID supplierId) {
        supplierService.deleteSupplier(supplierId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/supplier/name/{supplierName}")
    public ResponseEntity<SupplierResponseDTO> findSupplierByName(@PathVariable(name = "supplierName") String supplierName) {
        return ResponseEntity.ok(supplierService.findSupplierByName(supplierName));
    }
}
