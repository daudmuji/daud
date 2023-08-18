package com.daud.datamaster.mapper;

import com.daud.datamaster.dto.request.CategoryRequestDTO;
import com.daud.datamaster.dto.response.CategoryResponseDTO;
import com.daud.datamaster.entity.Category;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class CategoryMapper {

    public CategoryResponseDTO toDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setCategoryId(category.getId());
        dto.setCategoryName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setCreatedAt(category.getCreatedAt());
        dto.setUpdateAt(category.getUpdateAt());
        return dto;
    }

    public void updateEntity(CategoryRequestDTO dto, Category category) {
        category.setName(dto.getCategoryName()==null? category.getName() : dto.getCategoryName());
        category.setDescription(dto.getCategoryDescription()==null? category.getDescription() : dto.getCategoryDescription());
        category.setCreatedAt(category.getCreatedAt());
        category.setUpdateAt(LocalDate.now());
    }
}
