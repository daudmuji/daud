package com.daud.datamaster.service.impl;

import com.daud.datamaster.dto.request.ProductRequestDTO;
import com.daud.datamaster.dto.response.ProductResponseDTO;
import com.daud.datamaster.entity.Category;
import com.daud.datamaster.entity.Product;
import com.daud.datamaster.exception.ResourceNotFoundException;
import com.daud.datamaster.mapper.ProductMapper;
import com.daud.datamaster.repository.ProductRepository;
import com.daud.datamaster.service.CategoryService;
import com.daud.datamaster.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("productService")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CategoryService categoryService;


    @Override
    public void createProduct(ArrayList<ProductRequestDTO> dtos) {
        List<Product> list = dtos.stream().map((dto) -> {
            Category category = categoryService.findCategory(dto.getCategoryId());
            Product product = new Product();
            product.setName(dto.getProductName());
            product.setCreatedAt(LocalDate.now());
            product.setQuantity(dto.getQuantity());
            product.setDescription(dto.getDescription());
            product.setCategory(category);
            return product;
        }).collect(Collectors.toList());
        ArrayList<Product> products = new ArrayList<Product>(list);
        productRepository.saveAll(products);
    }

    @Override
    public ProductResponseDTO findProductById(Long id) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        return productMapper.toDTO(product);
    }

    @Override
    public void updateProduct(Long productId, ProductRequestDTO dto) {
        Product product = productRepository.findProductById(productId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        productMapper.updateEntity(dto, product);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findProductById(productId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        productRepository.delete(product);
    }

    @Override
    public ProductResponseDTO findProductByName(String productName) {
        Product product = productRepository.findProductByName(productName).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        return productMapper.toDTO(product);
    }

    @Override
    public Product findProduct(Long id) {
        return productRepository.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
    }

    @Override
    public ProductResponseDTO construcDTO(Product product) {
        return productMapper.toDTO(product);
    }
}
