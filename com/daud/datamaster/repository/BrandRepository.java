package com.daud.datamaster.repository;

import com.daud.datamaster.entity.Brand;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {


    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT b FROM brand b WHERE b.id =: id")
    public Optional<Brand> findBrandById(Long id);

    public Optional<Brand> findBrandByName(String brandName);

    public Optional<Brand> findBrandByCreatedAt(LocalDate createdAt);

    public Page<Brand> findBrandByNameLikeIgnoreCaseAndCreatedAt(String brandName, LocalDate createdAt, Pageable pageable);
}
