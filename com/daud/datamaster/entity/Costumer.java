package com.daud.datamaster.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "costumer")
@Data
@SQLDelete(sql = "UPDATE costumer SET deleted = TRUE WHERE id = ?")
@Where(clause = "deleted = FALSE")
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number_phone", length = 30, nullable = false)
    @Pattern(regexp = "\\+?[0-9]+")
    private String numberPhone;

    @Column
    private String address;

    @OneToMany(mappedBy = "costumer")
    private List<Transaction> transactionList;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updateAt;
}