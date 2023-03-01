package com.resellerapp.model.enums;

public enum ConditionEnum {
    EXCELLENT("Excellent"),
    GOOD("Good"),
    ACCEPTABLE("Acceptable");

    private final String value;

    private ConditionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
