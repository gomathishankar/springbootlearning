package org.gslearn.eazyschool.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.gslearn.eazyschool.validations.PasswordStrengthValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "Please choose a strong password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
