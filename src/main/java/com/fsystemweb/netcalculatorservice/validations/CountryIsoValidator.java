package com.fsystemweb.netcalculatorservice.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.Locale;

import static com.fsystemweb.netcalculatorservice.constants.VatList.RATE_LIST;

public class CountryIsoValidator implements ConstraintValidator<CountryIsoValidation, String> {
    public boolean isValid(String countryIso, ConstraintValidatorContext cxt) {
        if(countryIso == null)
            return false;
        countryIso = countryIso.toUpperCase(Locale.ENGLISH);
        return (RATE_LIST.containsKey(countryIso));
    }
}
