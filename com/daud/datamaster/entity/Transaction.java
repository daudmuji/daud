package com.daud.datamaster.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "transaction")
@SQLDelete(sql = "UPDATE transaction SET deleted = TRUE WHERE id = ?")
@Where(clause = "deleted = FALSE")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "costumer_id", referencedColumnName = "id")
    private Costumer costumer;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<TransactionDetail> transactionDetailList;

    @ManyToOne
    @JoinColumn(name = "profit_id::uuid", referencedColumnName = "id")
    private Profit profit;

    @Column(nullable = false)
    private Integer quantityProduct;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updateAt;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive = Boolean.TRUE;
}
