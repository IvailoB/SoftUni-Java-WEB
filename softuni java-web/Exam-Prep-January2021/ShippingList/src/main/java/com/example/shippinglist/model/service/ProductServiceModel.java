package com.example.shippinglist.model.service;

import com.example.shippinglist.model.entity.User;
import com.example.shippinglist.model.enums.CategoryName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductServiceModel {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private LocalDateTime neededBefore;

    private CategoryName category;

    private User user;
}
