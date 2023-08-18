package com.daud.datamaster.service.impl;

import com.daud.datamaster.dto.request.CategoryRequestDTO;
import com.daud.datamaster.dto.response.CategoryResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.entity.Category;
import com.daud.datamaster.exception.ResourceNotFoundException;
import com.daud.datamaster.mapper.CategoryMapper;
import com.daud.datamaster.repository.CategoryRepository;
import com.daud.datamaster.service.CategoryService;
import com.daud.datamaster.utill.PaginationUtill;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("categoryService")
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;
    @Override
    public void createCategory(ArrayList<CategoryRequestDTO> dtos) {
       List<Category> list = dtos.stream().map((dto) -> {
           Category category = new Category();
           category.setName(dto.getCategoryName());
           category.setDescription(dto.getCategoryDescription());
           category.setCreatedAt(LocalDate.now());
           return category;
       }).collect(Collectors.toList());
       ArrayList<Category> categories = new ArrayList<Category>(list);
       categoryRepository.saveAll(categories);
    }

    @Override
    public CategoryResponseDTO findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        return categoryMapper.toDTO(category);
    }

    @Override
    public ResultPageResponseDTO<CategoryResponseDTO> findCategoriesByName(Integer pages, Integer limit, String sortBy, String direction, String categoryName) {
        categoryName = StringUtils.isBlank(categoryName)?"%":"%"+categoryName+"%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtill.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Category> pageResult = categoryRepository.findCategoriesByNameLikeIgnoreCase(categoryName, pageable);
        List<CategoryResponseDTO> list = pageResult.stream().map(c -> {
            CategoryResponseDTO dto = new CategoryResponseDTO();
            dto.setCategoryId(c.getId());
            dto.setCategoryName(c.getName());
            dto.setDescription(c.getDescription());
            dto.setCreatedAt(c.getCreatedAt());
            dto.setUpdateAt(c.getUpdateAt());
            return dto;
        }).collect(Collectors.toList());
        ArrayList<CategoryResponseDTO> dtos = new ArrayList<>(list);
        return PaginationUtill.create(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    @Override
    public void updateCategory(Long categoryId, CategoryRequestDTO dto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        categoryMapper.updateEntity(dto, category);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("invalid id"));
        categoryRepository.delete(category);

    }

    @Override
    public CategoryResponseDTO findByName(String categoryName) {
        Category category = categoryRepository.findCategoryByName(categoryName).orElseThrow(() -> new ResourceNotFoundException("invalid category name"));
        return categoryMapper.toDTO(category);
    }

    @Override
    public Category findCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("invalid category name"));
    }

    @Override
    public CategoryResponseDTO construcDTO(Category category) {
        return categoryMapper.toDTO(category);
    }
}
