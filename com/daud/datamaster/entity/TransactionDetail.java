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
@Table(name = "transaction_detail")
@Data
@SQLDelete(sql = "UPDATE transaction_detail SET deleted = TRUE WHERE id = ?")
@Where(clause = "deleted = FALSE")
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_detail_generator")
    @SequenceGenerator(name = "transaction_detail_generator", sequenceName = "transaction_detail_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_detail_id", referencedColumnName = "id")
    private ProductDetail productDetail;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private BigDecimal amount;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive = Boolean.TRUE;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updateAt;
}
