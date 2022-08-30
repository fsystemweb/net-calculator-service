package com.fsystemweb.netcalculatorservice.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TaxRateProviderTest {

    TaxRateProvider taxRateProvider;

    @BeforeEach
    void setUp(){
        taxRateProvider = new TaxRateProvider();
    }

    @Test
    void shouldThrowAnExceptionWhenUseAnInvalidCountryIso(){
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            taxRateProvider.getTaxRate("AR2");
        }, "Exception was expected");

        Assertions.assertEquals("We not support this country iso: AR2", thrown.getMessage());
    }

    @Test
    void shouldThrowAnExceptionWhenUseANumericCountryIso(){
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            taxRateProvider.getTaxRate("12");
        }, "Exception was expected");

        Assertions.assertEquals("We not support this country iso: 12", thrown.getMessage());
    }

    @Test
    void shouldThrowAnExceptionWhenCountryIsoIsEmpty(){
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            taxRateProvider.getTaxRate("");
        }, "Exception was expected");

        Assertions.assertEquals("We not support this country iso: ", thrown.getMessage());
    }

    @Test
    void shouldReturnAValidTaxRate() throws Exception {
        Assertions.assertEquals(0.21, taxRateProvider.getTaxRate("AR"));
    }
}
