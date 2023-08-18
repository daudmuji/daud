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

@Data
@Entity
@Table(name = "storage")
@SQLDelete(sql = "UPDATE storage SET deleted = TRUE WHERE id = ?")
@Where(clause = "deleted = FALSE")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    @Pattern(regexp = "\\+?[0-9]+")
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "storage")
    private List<ProductDetail> productDetailList;

    @Column
    private LocalDate createdAt;

    @Column(name = "qty_before")
    private Integer quantityBefore;

    @Column
    private LocalDate updateAt;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

}