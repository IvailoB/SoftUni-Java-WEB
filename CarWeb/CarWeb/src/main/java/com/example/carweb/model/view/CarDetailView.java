package com.example.carweb.model.view;

import com.example.carweb.model.entity.Picture;
import com.example.carweb.model.enums.CoupeEnum;
import com.example.carweb.model.enums.EngineEnum;
import com.example.carweb.model.enums.StatusCarEnum;
import com.example.carweb.model.enums.Transmission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDetailView {
    private Long id;
    private String make;
    private String model;
    private String color;
    private BigDecimal price;
    private String description;
    private String year;
    private String kilometers;
    private CoupeEnum coupeEnum;
    private EngineEnum engineEnum;
    private StatusCarEnum status;
    private Transmission transmission;
    private String sellerName;
    private String townName;
    private Set<String> pictures;
}
