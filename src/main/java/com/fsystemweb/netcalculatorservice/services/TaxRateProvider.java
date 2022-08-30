package com.fsystemweb.netcalculatorservice.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class TaxRateProvider {

    private static final Map<String, Double> RATE_LIST = new HashMap<String, Double>()
    {
        {
            put("AU", 0.1);
            put("BZ", 0.125);
            put("DK", 0.25);
            put("EC", 0.35);
            put("FI", 0.20);
            put("IE", 0.23);
            put("FR", 0.2);
            put("SE", 0.25);
            put("CA", 0.15);
            put("IT", 0.22);
            put("SI", 0.22);
            put("AT", 0.20);
            put("ES", 0.21);
            put("AZ", 0.18);
            put("BW", 0.14);
            put("SK", 0.20);
            put("DE", 0.19);
            put("NL", 0.21);
            put("IL", 0.17);
            put("IS", 0.24);
            put("NO", 0.25);
            put("BR", 0.19);
            put("EE", 0.20);
            put("LV", 0.21);
            put("CY", 0.19);
            put("CN", 0.13);
            put("US", 0.115);
            put("UK", 0.20);
            put("JP", 0.10);
            put("PL", 0.23);
            put("DZ", 0.19);
            put("HU", 0.27);
            put("AL", 0.20);
            put("RS", 0.20);
            put("IN", 0.28);
            put("AR", 0.21);
        };
    };


    public double getTaxRate(String countryIso) throws Exception{
        countryIso = countryIso.toUpperCase(Locale.ENGLISH);
        if(RATE_LIST.containsKey(countryIso)){
            return RATE_LIST.get(countryIso);
        }else {
            throw new Exception("We not support this country iso: " + countryIso);
        }
    }
}
