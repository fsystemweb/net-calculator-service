package com.fsystemweb.netcalculatorservice.web;

import com.fsystemweb.netcalculatorservice.validations.CountryIsoValidation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class NetPriceRequest {

    @DecimalMin(value = "0.01", inclusive = false)
    @NotNull
    private double grossPrice;

    @NotNull
    @NotEmpty(message = "Please provide a country iso")
    @Size(min = 2, max = 2)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @CountryIsoValidation()
    private String countryIso;
}
