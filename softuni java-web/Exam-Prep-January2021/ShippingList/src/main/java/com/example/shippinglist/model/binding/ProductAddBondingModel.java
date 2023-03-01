package com.example.shippinglist.model.binding;


import com.example.shippinglist.model.enums.CategoryName;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductAddBondingModel {

    @Size(min = 3, max = 20, message = "Name must be between 3 and 20")
    @NotBlank(message = "Cannot be empty")
    private String name;

    @Size(min = 5, message = "Description must be more than 5")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd 'T' HH:mm")
    @FutureOrPresent(message = "Date cannot be in the past")
    private LocalDateTime neededBefore;

    @Positive(message = "price must be positive")
    private BigDecimal price;

    @NotNull(message = "SELECT CATEGORY")
    private CategoryName category;
}
