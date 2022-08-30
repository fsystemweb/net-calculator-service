package com.fsystemweb.netcalculatorservice.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Locale;


import static com.fsystemweb.netcalculatorservice.constants.VatList.RATE_LIST;

@Service
public class TaxRateProvider {
    public double getTaxRate(String countryIso) throws Exception{
        countryIso = countryIso.toUpperCase(Locale.ENGLISH);
        if(RATE_LIST.containsKey(countryIso)){
            return RATE_LIST.get(countryIso);
        }else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "We not support this country iso: " + countryIso
                            + ". Check the list of supported countries here:" +
                            " https://github.com/fsystemweb/net-calculator-service#supported-countries");
        }
    }
}
