package com.data.session08.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class ConfirmPasswordMatchingValidator implements ConstraintValidator<ConfirmPasswordMatching, Object> {
    private String passwordField;
    private String confirmPasswordField;

    @Override
    public void initialize(ConfirmPasswordMatching constraintAnnotation) {
        this.passwordField = constraintAnnotation.password();
        this.confirmPasswordField = constraintAnnotation.confirmPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(passwordField);
        Object confirmPasswordValue = new BeanWrapperImpl(value).getPropertyValue(confirmPasswordField);

        if (passwordValue == null || confirmPasswordValue == null) {
            return false;
        }

        return passwordValue.equals(confirmPasswordValue);
    }
}
