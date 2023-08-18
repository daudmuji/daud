package com.daud.datamaster.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "category")
@SQLDelete(sql = "UPDATE category SET deleted = TRUE WHERE id = ?")
@Where(clause = "deleted = FALSE")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    @SequenceGenerator(name = "category_generator", sequenceName = "category_id_seq")
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updateAt;
}
