package com.example.demo.validators;

import com.example.demo.domain.Part;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.example.demo.validators.ValidMinMax;

public class MinMaxValidator implements ConstraintValidator<ValidMinMax, Part> {

    @Override
    public void initialize(ValidMinMax constraintAnnotation) {
        // No initialization needed in this case
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if (part == null) {
            return true;  // Skip validation if part is null
        }

        int min = part.getMin();
        int max = part.getMax();

        if (min > max) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Minimum value cannot be greater than maximum value.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}

