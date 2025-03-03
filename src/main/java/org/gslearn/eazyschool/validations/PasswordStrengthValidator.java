package org.gslearn.eazyschool.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.gslearn.eazyschool.annotation.PasswordValidator;

import java.util.List;
import java.util.Objects;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {

    List<String> weakPasswords;

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        weakPasswords = List.of("12345", "password", "qwerty");
    }

    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.nonNull(passwordField) && (!weakPasswords.contains(passwordField));
    }
}
