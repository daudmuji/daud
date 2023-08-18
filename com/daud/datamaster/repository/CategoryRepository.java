package com.daud.datamaster.repository;

import com.daud.datamaster.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Optional<Category> findCategoryById(Long id);

    public Optional<Category> findCategoryByName(String categoryName);

    public Page<Category> findCategoriesByNameLikeIgnoreCase(String categoryName, Pageable pageable);
}
