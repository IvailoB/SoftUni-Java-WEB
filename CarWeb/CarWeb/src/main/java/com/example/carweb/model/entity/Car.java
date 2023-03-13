package com.example.carweb.model.entity;

import com.example.carweb.model.enums.CoupeEnum;
import com.example.carweb.model.enums.EngineEnum;
import com.example.carweb.model.enums.StatusCarEnum;
import com.example.carweb.model.enums.Transmission;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private String kilometers;

    @Enumerated(EnumType.STRING)
    private CoupeEnum coupeEnum;

    @Enumerated(EnumType.STRING)
    private EngineEnum engineEnum;

    @Enumerated(EnumType.STRING)
    private StatusCarEnum status;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @ManyToOne
    private User seller;

    @ManyToOne
    private Town town;

    @OneToMany
    private Set<Picture> pictures;
}
