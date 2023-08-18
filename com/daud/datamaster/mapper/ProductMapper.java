package com.daud.datamaster.mapper;

import com.daud.datamaster.dto.request.ProductRequestDTO;
import com.daud.datamaster.dto.response.ProductResponseDTO;
import com.daud.datamaster.entity.Category;
import com.daud.datamaster.entity.Product;
import com.daud.datamaster.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryService categoryService;

    public ProductResponseDTO toDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setQtyBefore(product.getQuantityBefore());
        dto.setUpdateAt(product.getUpdateAt());
        dto.setQuantity(product.getQuantity());
        dto.setDescription(product.getDescription());
        dto.setCategory(categoryService.construcDTO(product.getCategory()));
        return dto;
    }

    public void updateEntity(ProductRequestDTO dto, Product product) {

        Category category = categoryService.findCategory(dto.getCategoryId());
        product.setName(dto.getProductName()==null? product.getName() : dto.getProductName());
        product.setCreatedAt(product.getCreatedAt());
        product.setQuantityBefore(dto.getQtyBefore()==null? product.getQuantityBefore() : product.getQuantity());
        product.setUpdateAt(LocalDate.now());
        product.setQuantity(dto.getQuantity()==null? product.getQuantity() : dto.getQuantity());
        product.setDescription(dto.getDescription()==null? product.getDescription() : dto.getDescription());
        product.setCategory(dto.getCategoryId()==null? product.getCategory() : category);
    }
}
