package com.fsystemweb.netcalculatorservice.controllers;

import com.fsystemweb.netcalculatorservice.services.CalculeNetPriceService;
import com.fsystemweb.netcalculatorservice.services.TaxRateProvider;
import com.fsystemweb.netcalculatorservice.web.NetPriceRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class NetCalculatorControllerTest {
    @Autowired
    NetCalculatorController netCalculatorController;

    RestTemplate restTemplate;

    @BeforeEach
    void setUp(){
        restTemplate = new RestTemplate();
    }

    final String URL = "http://localhost:8080/net-price";

    @Test
    void shouldReturnBadRequestWhenUseAnEmptyGrossValue(){
        NetPriceRequest netPriceRequest = new NetPriceRequest();

        netPriceRequest.setCountryIso("AR");

        HttpEntity<NetPriceRequest> request = new HttpEntity<>(netPriceRequest);

        try {
            ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
        }catch (HttpClientErrorException e){
            Assertions.assertEquals(e.getStatusCode(), HttpStatus.BAD_REQUEST);
        }

    }

    @Test
    void shouldReturnBadRequestWhenUseAnEmptyCountryIso(){
        NetPriceRequest netPriceRequest = new NetPriceRequest();

        netPriceRequest.setGrossPrice(2000.22);

        HttpEntity<NetPriceRequest> request = new HttpEntity<>(netPriceRequest);

        try {
            ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
        }catch (HttpClientErrorException e){
            Assertions.assertEquals(e.getStatusCode(), HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    void shouldReturnBadRequestWhenUseAnInvalidGrossPrice(){
        NetPriceRequest netPriceRequest = new NetPriceRequest();

        netPriceRequest.setGrossPrice(0);
        netPriceRequest.setCountryIso("AR");

        HttpEntity<NetPriceRequest> request = new HttpEntity<>(netPriceRequest);

        try {
            ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
        }catch (HttpClientErrorException e){
            Assertions.assertEquals(e.getStatusCode(), HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    void shouldReturnBadRequestWhenUseANumericCountryIso(){
        NetPriceRequest netPriceRequest = new NetPriceRequest();

        netPriceRequest.setGrossPrice(1000);
        netPriceRequest.setCountryIso("12");

        HttpEntity<NetPriceRequest> request = new HttpEntity<>(netPriceRequest);

        try {
            ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
        }catch (HttpClientErrorException e){
            Assertions.assertEquals(e.getStatusCode(), HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    void shouldReturnBadRequestWhenUseAnInvalidCountryIso(){
        NetPriceRequest netPriceRequest = new NetPriceRequest();

        netPriceRequest.setGrossPrice(1000);
        netPriceRequest.setCountryIso("ARS");

        HttpEntity<NetPriceRequest> request = new HttpEntity<>(netPriceRequest);

        try {
            ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
        }catch (HttpClientErrorException e){
            Assertions.assertEquals(e.getStatusCode(), HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    void shouldAMessageWhenUseAnNotSupportedCountryIso(){
        NetPriceRequest netPriceRequest = new NetPriceRequest();

        netPriceRequest.setGrossPrice(1000);
        netPriceRequest.setCountryIso("PR");

        HttpEntity<NetPriceRequest> request = new HttpEntity<>(netPriceRequest);


        ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);

        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(result.getBody(), "We not support this country iso: PR");
    }

    @Test
    void shouldReturnAValidNetPrice(){
        NetPriceRequest netPriceRequest = new NetPriceRequest();

        netPriceRequest.setGrossPrice(1000);
        netPriceRequest.setCountryIso("AR");

        HttpEntity<NetPriceRequest> request = new HttpEntity<>(netPriceRequest);


        ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);

        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(result.getBody(), "1210.00");
    }
}
