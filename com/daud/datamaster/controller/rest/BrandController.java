package com.daud.datamaster.controller.rest;

import com.daud.datamaster.dto.request.BrandRequestDTO;
import com.daud.datamaster.dto.response.BrandResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/brand")
    public ResponseEntity<Void> createNewBrand(@Valid @RequestBody ArrayList<BrandRequestDTO> dtos) {
        brandService.createBrands(dtos);
        return ResponseEntity.created(URI.create("/brand")).build();
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<BrandResponseDTO> findBrandById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(brandService.findBrandById(id));
    }
    @GetMapping("/brands")
    public ResponseEntity<ResultPageResponseDTO<BrandResponseDTO>> findBrands(
            @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
            @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
            @RequestParam(name = "brandName", required = false) String brandName,
            @RequestParam(name = "createdAt", required = false)String createdAt
            ) {
        LocalDate dateTime = LocalDate.parse(createdAt);
        return ResponseEntity.ok(brandService.findBrands(pages, limit, sortBy, direction, brandName, dateTime));
    }

    @PutMapping("/brand/{brandId}")
    public ResponseEntity<Void> updateBrandById(@PathVariable(name = "brandId") Long brandId,
                                                @RequestBody BrandRequestDTO dto) {
        brandService.updateBrand(brandId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/brand/{brandId}")
    public ResponseEntity<Void> deleteBrandById(@PathVariable(name = "brandId") Long brandId) {
        brandService.deleteBrand(brandId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/brand/name/{brandName}")
    public ResponseEntity<BrandResponseDTO> findBrandByName(@PathVariable(name = "brandName") String brandName) {
        return ResponseEntity.ok(brandService.findBrandByName(brandName));
    }
}
