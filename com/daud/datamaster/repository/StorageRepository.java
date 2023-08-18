package com.daud.datamaster.repository;

import com.daud.datamaster.entity.ProductDetail;
import com.daud.datamaster.entity.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StorageRepository extends JpaRepository<Storage, UUID> {

    public Optional<Storage> findById(UUID id);

    public Optional<Storage> findByName(String nameStorageLocation);

    public Page<Storage> findStoragesByNameLikeIgnoreCase(String storageName, Pageable pageable);

    @Query("SELECT pd FROM ProductDetail pd " +
            "JOIN pd.storage s " +
            "WHERE s.id = :storageId ")
    public List<ProductDetail> findProductsByStorageId(UUID storageId);
}
