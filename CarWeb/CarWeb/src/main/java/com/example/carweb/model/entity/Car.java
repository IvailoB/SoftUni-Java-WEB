package com.example.carweb.model.entity;

import com.example.carweb.model.enums.CoupeEnum;
import com.example.carweb.model.enums.EngineEnum;
import com.example.carweb.model.enums.StatusCarEnum;
import com.example.carweb.model.enums.Transmission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Picture> pictures;


    public Car(String make, String model, String color, BigDecimal price, String description, String year, String kilometers) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.price = price;
        this.description = description;
        this.year = year;
        this.kilometers = kilometers;
    }

}
