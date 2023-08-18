package com.daud.datamaster.service;

import com.daud.datamaster.dto.request.CategoryRequestDTO;
import com.daud.datamaster.dto.response.CategoryResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.entity.Category;

import java.util.ArrayList;

public interface CategoryService {

    public void createCategory(ArrayList<CategoryRequestDTO> dtos);

    public CategoryResponseDTO findById(Long id);

    public ResultPageResponseDTO<CategoryResponseDTO> findCategoriesByName(Integer pages, Integer limit, String sortBy,
                                                                           String direction, String categoryName);

    public void updateCategory(Long categoryId, CategoryRequestDTO dto);

    public void deleteCategory(Long categoryId);

    public CategoryResponseDTO findByName(String categoryName);

    public Category findCategory(Long id);

    public CategoryResponseDTO construcDTO(Category category);
}
