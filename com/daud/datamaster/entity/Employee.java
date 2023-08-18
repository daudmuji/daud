package com.daud.datamaster.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table
@SQLDelete(sql = "UPDATE employee SET deleted = TRUE WHERE id = ?")
@Where(clause = "deleted = FALSE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String position;

    @Column(nullable = false)
    private BigDecimal salary;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updateAt;
}