package com.daud.datamaster.controller.rest;

import com.daud.datamaster.dto.request.CategoryRequestDTO;
import com.daud.datamaster.dto.response.CategoryResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.service.CategoryService;
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
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<Void> createNewCategory(@Valid @RequestBody ArrayList<CategoryRequestDTO> dtos) {
        categoryService.createCategory(dtos);
        return ResponseEntity.created(URI.create("/category")).build();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponseDTO> findCategoryById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    public ResponseEntity<ResultPageResponseDTO<CategoryResponseDTO>> findCategories(
           @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
           @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
           @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
           @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
           @RequestParam(name = "categoryName", required = false) String categoryName
    ) {
        return ResponseEntity.ok(categoryService.findCategoriesByName(pages, limit, sortBy, direction, categoryName));
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<Void> updateCategoryById(@PathVariable(name = "categoryId") Long categoryId,
                                                   @RequestBody CategoryRequestDTO dto) {
        categoryService.updateCategory(categoryId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/name/{categoryName}")
    public ResponseEntity<CategoryResponseDTO> findCategoryByName(@PathVariable(name = "categoryName") String categoryName) {
        return ResponseEntity.ok(categoryService.findByName(categoryName));
    }

}
