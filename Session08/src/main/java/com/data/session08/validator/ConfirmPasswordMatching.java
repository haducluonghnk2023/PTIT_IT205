package com.data.session08.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConfirmPasswordMatchingValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPasswordMatching {
    String message() default "Mật khẩu và xác nhận mật khẩu không khớp";

    String password();
    String confirmPassword();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
