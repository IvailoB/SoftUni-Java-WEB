package com.resellerapp.model.dto;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OfferAddDTO {

    private Long id;

    @Size(min = 2, max = 50, message = "Content length must be between 2 and 50 characters!")
    @NotBlank
    private String description;

    @Positive(message = "price must be positive number")
    private BigDecimal price;

    @NotNull(message = "You must select a condition!")
    private ConditionEnum condition;
}
