package com.fsystemweb.netcalculatorservice.controllers;

import com.fsystemweb.netcalculatorservice.services.CalculeNetPriceService;
import com.fsystemweb.netcalculatorservice.web.NetPriceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class NetCalculatorController {
    @Autowired
    CalculeNetPriceService calculeNetPriceService;


    @RequestMapping(value = "/net-price", method = RequestMethod.POST)
    String getNetPrice(@Valid @RequestBody NetPriceRequest netPriceRequest) {

        try{
            return calculeNetPriceService.getNetPrice(netPriceRequest.getGrossPrice(), netPriceRequest.getCountryIso()).toString();
        }
        catch(Exception e){
            return e.getMessage();
        }

    }
}
