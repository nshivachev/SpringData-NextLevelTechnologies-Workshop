package com.softuni.nextleveltechnologies.utils;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtils {
    private final Validator validator;

    public ValidationUtils() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).isEmpty();
    }
}
