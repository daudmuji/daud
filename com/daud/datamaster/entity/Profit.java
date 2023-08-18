package com.daud.datamaster.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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
@Table(name = "profit")
@SQLDelete(sql = "UPDATE profit SET deleted = TRUE WHERE id = ?")
@Where(clause = "deleted = FALSE")
public class Profit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "profit")
    private List<Transaction> transactionList;

    @Column
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updateAt;
}
