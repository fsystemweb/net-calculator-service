package com.fsystemweb.netcalculatorservice.controllers;

import com.fsystemweb.netcalculatorservice.services.CalculeNetPriceService;
import com.fsystemweb.netcalculatorservice.web.ErrorResponse;
import com.fsystemweb.netcalculatorservice.web.NetPriceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@RestController
@Validated
public class NetCalculatorController {
    @Autowired
    CalculeNetPriceService calculeNetPriceService;


    @RequestMapping(value = "/net-price", method = RequestMethod.POST)
    String getNetPrice(@Valid @RequestBody NetPriceRequest netPriceRequest) throws Exception {

        return calculeNetPriceService.getNetPrice(netPriceRequest.getGrossPrice(), netPriceRequest.getCountryIso()).toString();

    }


    @ExceptionHandler({ HttpClientErrorException.class, NullPointerException.class,
            IllegalStateException.class})
    public ErrorResponse handleException(HttpClientErrorException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class})
    public ErrorResponse handleException(MethodArgumentNotValidException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), exception.getFieldError().getDefaultMessage());
    }

}
