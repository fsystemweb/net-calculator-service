package com.fsystemweb.netcalculatorservice.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CountryIsoValidator.class)
public @interface CountryIsoValidation {
    //error message
    public String message() default "We not support this country iso. " +
            "Check the list of supported countries here: " +
            "https://github.com/fsystemweb/net-calculator-service#supported-countries";
    //represents group of constraints
    public Class<?>[] groups() default {};
    //represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};
}
