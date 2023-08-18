package com.daud.datamaster.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Table
@Entity(name = "brand")
@SQLDelete(sql = "UPDATE brand SET deleted = TRUE WHERE id = ?")
@Where(clause = "deleted = FALSE")
@Data
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_generator")
    @SequenceGenerator(name = "brand_generator", sequenceName = "brand_id_seq")
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "supplier_id::uuid", referencedColumnName = "id")
    private Supplier supplier;

    @Column
    private LocalDate createdAt;

    @JoinColumn(name = "qty_before", referencedColumnName = "qty_before")
    private Integer quantityBefore;

    @JoinColumn(name = "qty_add", referencedColumnName = "qty_add")
    private Integer quantityAdd;

    @Column
    private LocalDate updateAt;

    @JoinColumn(name = "quantity", referencedColumnName = "quantity")
    private Integer quantitySupplier;

    @Column
    private String description;

    @OneToMany(mappedBy = "brand")
    private List<ProductDetail> productDetailList;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

}
