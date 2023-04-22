package com.example.vnet.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "sales-data", groupId = "myGroup")
    public void processMessage(String content){
        System.out.println("Message received: " + content);
    }
}
