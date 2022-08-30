package com.fsystemweb.netcalculatorservice.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculeNetPriceServiceTest {

    @InjectMocks
    CalculeNetPriceService calculeNetPriceService;
    @Mock TaxRateProvider taxRateProvider;


    @Test
    void shouldReturnAValidNetPrice() throws Exception {
        when(taxRateProvider.getTaxRate(anyString())).thenReturn(0.21);

        BigDecimal result = calculeNetPriceService.getNetPrice(200, "AR");

        BigDecimal expected = new BigDecimal("242.00");
        assertEquals(expected, result);
    }

    @Test
    void shouldReturnAValidNetPriceWith2Decimals() throws Exception {
        when(taxRateProvider.getTaxRate(anyString())).thenReturn(0.21);

        BigDecimal result = calculeNetPriceService.getNetPrice(200.23, "AR");

        BigDecimal expected = new BigDecimal("242.28");
        assertEquals(expected, result);
    }

    @Test
    void shouldReturnZero() throws Exception {
        when(taxRateProvider.getTaxRate(anyString())).thenReturn(0.21);

        BigDecimal result = calculeNetPriceService.getNetPrice(0, "AR");

        BigDecimal expected = new BigDecimal("0.00");
        assertEquals(expected, result);
    }

}
