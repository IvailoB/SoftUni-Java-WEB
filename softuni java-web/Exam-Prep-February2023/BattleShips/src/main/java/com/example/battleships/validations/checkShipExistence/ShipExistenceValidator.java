package com.example.battleships.validations.checkShipExistence;

import com.example.battleships.repository.ShipRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ShipExistenceValidator implements ConstraintValidator<ValidateExistenceOfShip, String> {

    private final ShipRepository shipRepository;

    @Autowired
    public ShipExistenceValidator(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public void initialize(ValidateExistenceOfShip constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String shipName, ConstraintValidatorContext context) {

        return shipRepository.findByName(shipName).isEmpty();
    }
}
