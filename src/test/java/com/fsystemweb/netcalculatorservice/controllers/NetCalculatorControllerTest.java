package com.fsystemweb.netcalculatorservice.controllers;


import com.fsystemweb.netcalculatorservice.web.NetPriceRequest;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NetCalculatorControllerTest {
    @InjectMocks
    NetCalculatorController netCalculatorController;

    @Mock
    private RestTemplate restTemplate;


    final String URL = "http://localhost:8080/net-price";

    @Test
    void shouldReturnBadRequestWhenUseAnEmptyGrossValue(){
        NetPriceRequest netPriceRequest = new NetPriceRequest();

        netPriceRequest.setCountryIso("AR");

        HttpEntity<NetPriceRequest> request = new HttpEntity<>(netPriceRequest);

        when(restTemplate.exchange(URL, HttpMethod.POST, request, String.class)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));


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

        when(restTemplate.exchange(URL, HttpMethod.POST, request, String.class)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

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

        when(restTemplate.exchange(URL, HttpMethod.POST, request, String.class)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

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

        when(restTemplate.exchange(URL, HttpMethod.POST, request, String.class)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

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

        when(restTemplate.exchange(URL, HttpMethod.POST, request, String.class)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

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

        when(restTemplate.exchange(URL, HttpMethod.POST, request, String.class)).thenReturn(ResponseEntity.ok("We not support this country iso: PR"));

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

        when(restTemplate.exchange(URL, HttpMethod.POST, request, String.class)).thenReturn(ResponseEntity.ok("1210.00"));

        ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);

        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(result.getBody(), "1210.00");
    }
}
