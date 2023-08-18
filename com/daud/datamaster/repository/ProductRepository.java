package com.daud.datamaster.repository;

import com.daud.datamaster.dto.ProductQueryDTO;
import com.daud.datamaster.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findProductById(Long id);

    public Optional<Product> findProductByName(String productName);

    @Query("SELECT DISTINCT new com.daud.datamaster.dto.ProductQueryDTO(p.id, p.name, p.createdAt, p.quantityBefore, p.updateAt, p.quantity, p.description, c.name) " +
            "FROM Product p " +
            "INNER JOIN Category c ON c.id = p.category.id " +
            "WHERE LOWER(c.name) LIKE LOWER(CONCAT('%',:categoryName,'%')) " +
            "AND LOWER(p.name) LIKE LOWER(CONCAT('%',:productName,'%') ) ")
    public Page<ProductQueryDTO> findProductsByNameLikeIgnoreCaseAndCategory(String productName, String categoryName, Pageable pageable);

}
