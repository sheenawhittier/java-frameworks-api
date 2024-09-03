package com.example.demo.validators;

import com.example.demo.domain.Part;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InvValidator implements ConstraintValidator<ValidInv, Part> {

    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if (part.getInv() < part.getMin()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Inventory cannot be less than the minimum value.")
                    .addPropertyNode("inv")
                    .addConstraintViolation();
            return false;
        } else if (part.getInv() > part.getMax()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Inventory cannot be greater than the maximum value.")
                    .addPropertyNode("inv")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}

