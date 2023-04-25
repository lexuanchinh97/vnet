package com.example.vnet.controller;

import com.example.vnet.model.AggregationType;
import com.example.vnet.model.SalesDataDto;
import com.example.vnet.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    // http://localhost:8081/sales?search=units>1 and units<10&aggregationType=PRODUCT
    @GetMapping("/sales")
    @CrossOrigin("http://localhost:3000")
    public List<SalesDataDto> aggregateByProductAndStore(@RequestParam(value = "search", required = false) String search,
                                                         @RequestParam(value = "aggregationType", required = false) AggregationType aggregationType) {
        return salesService.aggregateByProductAndStore(search, aggregationType);
    }
}

