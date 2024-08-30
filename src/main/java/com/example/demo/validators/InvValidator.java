package com.example.demo.validators;

import com.example.demo.domain.Part;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InvValidator implements ConstraintValidator<ValidInv, Part> {

    @Override
    public void initialize(ValidInv constraintAnnotation) {
        // No initialization needed in this case
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if (part == null) {
            return true;  // Skip validation if part is null
        }

        int inv = part.getInv();
        int min = part.getMin();
        int max = part.getMax();

        if (inv < min || inv > max) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Inventory must be between the minimum (" + min + ") and maximum (" + max + ") values.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}

