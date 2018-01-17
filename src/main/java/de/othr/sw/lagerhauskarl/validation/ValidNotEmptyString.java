package de.othr.sw.lagerhauskarl.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Size;

@Constraint(validatedBy = {})
@Size(min=1)
@ReportAsSingleViolation
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
public @interface ValidNotEmptyString
{
  String message() default "{NotEmptyString.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}