package com.java.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = CcardDateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CcardDateConstraint {
	String message() default "Invalid expiration date. Must be of format MM/YY";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
