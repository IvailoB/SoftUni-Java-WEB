package com.example.carweb.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "towns")
public class Town extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private Integer postcode;
}
