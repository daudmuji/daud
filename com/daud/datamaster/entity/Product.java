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
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted = TRUE WHERE id = ?")
@Where(clause = "deleted = FALSE")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name = "product_generator", sequenceName = "product_id_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private LocalDate createdAt;

    @Column(name = "qty_before")
    private Integer quantityBefore;

    @Column
    private LocalDate updateAt;

    @Column(name = "quantity")
    private Integer quantity;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ProductDetail> productDetailList;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

}
