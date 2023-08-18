package com.daud.datamaster.controller.rest;

import com.daud.datamaster.dto.request.ProductRequestDTO;
import com.daud.datamaster.dto.response.ProductResponseDTO;
import com.daud.datamaster.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Void> createNewProduct(@Valid @RequestBody ArrayList<ProductRequestDTO> dtos) {
        productService.createProduct(dtos);
        return ResponseEntity.created(URI.create("/product")).build();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDTO> findProductById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable(name = "productId")Long productId, @RequestBody ProductRequestDTO dto) {
        productService.updateProduct(productId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "productId")Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/product/name/{productName}")
    public ResponseEntity<ProductResponseDTO> findProductByName(@PathVariable(name = "productName") String productName) {
        return ResponseEntity.ok(productService.findProductByName(productName));
    }
}
