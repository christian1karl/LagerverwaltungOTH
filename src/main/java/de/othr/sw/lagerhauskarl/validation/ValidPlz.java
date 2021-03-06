package de.othr.sw.lagerhauskarl.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

@Constraint(validatedBy = {})
@Pattern(regexp = "\\d{5}")
@ReportAsSingleViolation
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
public @interface ValidPlz
{
  String message() default "{ValidPlz.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}