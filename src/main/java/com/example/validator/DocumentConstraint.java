package com.example.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DocumentValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentConstraint {
    String message() default "Invalid document";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
