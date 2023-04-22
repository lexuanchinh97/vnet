package com.example.vnet.controller;

import com.example.vnet.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/send/{topic}/{message}")
    public String sendMessage(@PathVariable("topic") String topic, @PathVariable("message") String message) {
        kafkaProducer.sendMessage(topic, message);
        return "Sent message: " + message + " to topic: " + topic;
    }
}

