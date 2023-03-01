package com.example.battleships.domain.entity;

import com.example.battleships.domain.enums.CategoryType;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private CategoryType name;

    @Column(columnDefinition = "TEXT")
    private String description;
}
