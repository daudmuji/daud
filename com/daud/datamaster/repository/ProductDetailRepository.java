package com.daud.datamaster.repository;

import com.daud.datamaster.entity.ProductDetail;
import com.daud.datamaster.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    public Optional<ProductDetail> findById(Long id);

    public List<ProductDetail> findProductDetailsByBrandId(Long brandId);

    @Query("SELECT td FROM TransactionDetail td " +
            "JOIN td.productDetail pd " +
            "WHERE  td.isActive = true " +
            "AND pd.id = :productDetailId")
    public List<TransactionDetail>findTransactionByProductDetailAndActive(Long productDetailId);
}
