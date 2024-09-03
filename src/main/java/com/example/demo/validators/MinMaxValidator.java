package com.example.demo.validators;

import com.example.demo.domain.Part;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.example.demo.validators.ValidMinMax;

public class MinMaxValidator implements ConstraintValidator<ValidMinMax, Part> {

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if (part.getMin() > part.getMax()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Minimum value cannot be greater than the maximum value.")
                    .addPropertyNode("min")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}

