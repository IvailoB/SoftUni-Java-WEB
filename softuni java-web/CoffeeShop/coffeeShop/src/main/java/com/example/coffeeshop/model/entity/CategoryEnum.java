package com.example.coffeeshop.model.entity;

public enum CategoryEnum {

    COFFEE("Coffee"),
    CAKE("Cake"),
    DRINK("Drink"),

    OTHER("Other");

    private final String value;

    private CategoryEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
