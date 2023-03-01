package com.example.battleships.domain.model.binding;

import com.example.battleships.domain.enums.CategoryType;
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
public class ShipAddModel {

    @Size(min = 2,max = 10)
    @NotNull
    @ValidateExistenceOfShip
    private String name;

    @Positive
    @NotNull
    private Long health;

    @Positive
    @NotNull
    private Long power;

    @PastOrPresent
    @NotNull
    private LocalDate created;

    @NotNull
    private CategoryType category;
}
