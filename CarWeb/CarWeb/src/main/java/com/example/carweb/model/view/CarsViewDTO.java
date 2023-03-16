package com.example.carweb.model.view;

import com.example.carweb.model.enums.StatusCarEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarsViewDTO {
    private Long id;
    private String make;
    private String model;
    private BigDecimal price;
    private String thumbnailUrl;
    private StatusCarEnum status;
}
