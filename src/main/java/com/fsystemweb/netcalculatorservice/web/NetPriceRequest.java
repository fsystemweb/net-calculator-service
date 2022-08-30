package com.fsystemweb.netcalculatorservice.web;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class NetPriceRequest {
    @NotNull
    @DecimalMin("0.01")
    private double grossPrice;

    @NotEmpty(message = "Please provide a country iso")
    @Size(min = 2, max = 2)
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String countryIso;
}
