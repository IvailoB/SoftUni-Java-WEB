package com.example.carweb.model.dtos;

import com.example.carweb.model.entity.Picture;
import com.example.carweb.model.enums.CoupeEnum;
import com.example.carweb.model.enums.EngineEnum;
import com.example.carweb.model.enums.StatusCarEnum;
import com.example.carweb.model.enums.Transmission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class AddCarDTO {

    private Long id;

    @NotBlank(message = "cannot be empty")
    private String make;

    @NotBlank(message = "cannot be empty")
    private String model;

    @NotBlank(message = "cannot be empty")
    private String color;

    @Positive(message = "price must be positive number")
    private BigDecimal price;

    @NotBlank(message = "cannot be empty")
    private String description;

    @NotBlank(message = "cannot be empty")
    private String year;

    @NotBlank(message = "cannot be empty")
    private String kilometers;

    @NotNull(message = "You must select a coupe!")
    private CoupeEnum coupe;

    @NotNull(message = "You must select a engine!")
    private EngineEnum engine;

    @NotNull(message = "You must select a status!")
    private StatusCarEnum status;

    @NotNull(message = "You must select a transmission!")
    private Transmission transmission;

    @NotBlank(message = "cannot be empty")
    private String townName;

    @Positive(message = "Enter valid postcode")
    private Integer postcode;

    private MultipartFile picture;
}
