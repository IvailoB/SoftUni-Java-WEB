package com.example.carweb.model.view;

import com.example.carweb.model.entity.Picture;
import com.example.carweb.model.enums.StatusCarEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CarsWithUsernamesDTO {
    private Long id;
    private String make;
    private String model;
    private BigDecimal price;
    private Set<Picture> pictures;
    private StatusCarEnum status;
}
