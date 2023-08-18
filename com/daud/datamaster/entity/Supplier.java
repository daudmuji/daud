package com.daud.datamaster.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "supplier")
@SQLDelete(sql = "UPDATE supplier SET deleted = TRUE WHERE id = ?")
@Where(clause = "deleted = FALSE")
public class Supplier {

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

    @Column
    private LocalDate createdAt;

    @Column(name = "qty_before")
    private Integer quantityBefore;

    @Column(name = "qty_add")
    private Integer quantityAdd;

    @Column
    private LocalDate updateAt;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @OneToOne(mappedBy = "supplier", cascade = CascadeType.ALL)
    private Brand brand;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

}
