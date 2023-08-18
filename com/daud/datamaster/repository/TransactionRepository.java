package com.daud.datamaster.repository;

import com.daud.datamaster.dto.TransactionQueryDTO;
import com.daud.datamaster.dto.response.TransactionResponseDTO;
import com.daud.datamaster.entity.Transaction;
import com.daud.datamaster.entity.TransactionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    public Optional<Transaction> findTransactionById(UUID id);

    @Query("SELECT td FROM TransactionDetail td " +
            "JOIN td.transaction t " +
            "WHERE t.id =:transactionId")
    public List<TransactionDetail> findTransactionByTransactionId(UUID transactionId);

    @Query("SELECT DISTINCT new com.daud.datamaster.dto.TransactionQueryDTO(t.id, t.amount, t.quantityProduct, t.createdAt, t.updateAt, c.name) " +
            "FROM Transaction t " +
            "INNER JOIN Costumer c ON c.id = t.costumer.id " +
            "WHERE LOWER(c.name) LIKE LOWER(CONCAT('%',:costumerName,'%')) ")
    public Page<TransactionQueryDTO> findTransactionsByCostumerName(String costumerName, Pageable pageable);
}
