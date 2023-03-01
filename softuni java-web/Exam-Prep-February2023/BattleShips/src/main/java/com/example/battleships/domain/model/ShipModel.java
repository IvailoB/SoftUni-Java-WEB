package com.example.battleships.domain.model;

import com.example.battleships.validations.checkShipExistence.ValidateExistenceOfShip;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShipModel {

    private Long id;

    private String name;

    private Long health;

    private Long power;

    private LocalDate created;

    private CategoryModel category;

    private UserModel user;
}
