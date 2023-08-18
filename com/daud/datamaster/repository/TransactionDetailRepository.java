package com.daud.datamaster.repository;

import com.daud.datamaster.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {

    public Optional<TransactionDetail> findById(Long id);

}
