package com.example.vnet.controller;

import com.example.vnet.model.SalesData;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/salesData")
    @SendTo("/topic/salesData")
    public SalesData handleSalesData(@Payload SalesData salesData) {
        return salesData;
    }
}

