package com.daud.datamaster.controller.rest;

import com.daud.datamaster.dto.request.ProductDetailReqDTO;
import com.daud.datamaster.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    @PostMapping("/product-detail")
    public ResponseEntity<Void> createNewPD(@RequestBody ArrayList<ProductDetailReqDTO> dtos) {
        productDetailService.createPD(dtos);
        return ResponseEntity.created(URI.create("/product-detail")).build();
    }

    @PutMapping("/product-detail/quantity-brand/{brandId}")
    public ResponseEntity<Void> setQuantity(@PathVariable(name = "brandId") Long brandId, @RequestBody List<ProductDetailReqDTO> dtos) {
        productDetailService.setQuantitySumEqualsBrand(brandId, dtos);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/product-detail/{productDetailId}")
    public ResponseEntity<Void> updatePD(@PathVariable(name = "productDetailId") Long productDetailId, @RequestBody ProductDetailReqDTO dto) {
        productDetailService.updatePD(productDetailId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product-detail/{productDetailId}")
    public ResponseEntity<Void> deletePD(@PathVariable(name = "productDetailId") Long productDetailId) {
        productDetailService.deletePD(productDetailId);
        return ResponseEntity.ok().build();
    }

}
