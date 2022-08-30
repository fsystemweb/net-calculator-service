package com.fsystemweb.netcalculatorservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculeNetPriceService {

    final int ROUND_SIZE = 2;

    @Autowired
    TaxRateProvider taxRateProvider;

    public BigDecimal getNetPrice(double grossPrice, String countryIso) throws Exception{
        double TAX_RATE = taxRateProvider.getTaxRate(countryIso);

        return new BigDecimal(this.calculation(grossPrice, TAX_RATE)).setScale(ROUND_SIZE, RoundingMode.HALF_UP);
    }

    private double calculation(double grossPrice, double TAX_RATE){
        return (grossPrice * TAX_RATE) + grossPrice;
    }
}
