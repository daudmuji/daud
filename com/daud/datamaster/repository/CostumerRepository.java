package com.daud.datamaster.repository;

import com.daud.datamaster.entity.Costumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CostumerRepository extends JpaRepository<Costumer, UUID> {

    public Optional<Costumer> findCostumerById(UUID id);

    public Optional<Costumer> findCostumerByName(String costumerName);

    public Page<Costumer> findCostumersByNameLikeIgnoreCase(String costumerName, Pageable pageable);

}
