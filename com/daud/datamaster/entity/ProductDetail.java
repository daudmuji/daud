package com.daud.datamaster.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "product_detail")
@Data
@SQLDelete(sql = "UPDATE product_detail SET deleted = TRUE WHERE id = ?")
@Where(clause = "deleted = FALSE")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_detail_generator")
    @SequenceGenerator(name = "product_detail_generator", sequenceName = "product_detail_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @Column
    private LocalDate createdAt;

    @JoinColumn(name = "qty_brand_before", referencedColumnName = "qty_before")
    private Integer quantityBrandBefore;

    @JoinColumn(name = "quantity_brand", referencedColumnName = "quantity")
    private Integer quantityBrand;

    @Column(name = "qty_sum_brand")
    private Integer quantitySum;

    @Column(name = "qty_before")
    private Integer quantityBefore;

    @Column(name = "qty_add")
    private Integer quantityAdd;

    @OneToMany(mappedBy = "productDetail")
    private List<TransactionDetail> transactionDetailList;

    @Column
    private LocalDate updateAt;

    @Column(name = "quantity")
    private Integer quantity;

    @Column
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id::uuid", referencedColumnName = "id")
    private Storage storage;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

}
