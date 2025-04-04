package org.gslearn.eazyschool.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.gslearn.eazyschool.validations.FieldsValueMatchValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {

    String message() default "Fields value does not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String field();
    String fieldMatch();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
}
