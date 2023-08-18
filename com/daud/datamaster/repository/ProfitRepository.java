package com.daud.datamaster.repository;

import com.daud.datamaster.entity.Profit;
import com.daud.datamaster.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfitRepository extends JpaRepository<Profit, UUID> {

    public Optional<Profit> findProfitById(UUID id);

    public Optional<Profit> findProfitByAmount(BigDecimal amountProfit);

    @Query("SELECT t FROM Transaction t " +
            "JOIN t.profit p " +
            "WHERE p.id = :profitId")
    public List<Transaction> findTransactionsByProfitId(UUID profitId);

    public Optional<Profit> findProfitByCreatedAt(LocalDate createdAt);

    public Page<Profit> findProfitsByCreatedAt(LocalDate createdAt, Pageable pageable);

}
