package com.daud.datamaster.repository;

import com.daud.datamaster.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    public Optional<Supplier> findSupplierById(UUID id);

    public Optional<Supplier> findSupplierByName(String supplierName);

    public Page<Supplier> findSuppliersByNameLikeIgnoreCase(String supplierName, Pageable pageable);
}
