package com.example.vnet.service;

import com.example.vnet.model.AggregationType;
import com.example.vnet.model.SalesData;
import com.example.vnet.model.SalesDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final SimpMessagingTemplate messagingTemplate;
    private final SalesService salesService;

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "myGroup")
    public void consume(String message) {
        saveSaleData(message);
        List<SalesDataDto> payload = salesService.aggregateByProductAndStore(null, AggregationType.ALL);
        messagingTemplate.convertAndSend("/topic/salesData", payload);
    }

    private void saveSaleData(String message) {
        String[] fields = message.split("\\|");
        SalesData salesData = new SalesData();
        salesData.setDate(LocalDate.parse(fields[0], DateTimeFormatter.BASIC_ISO_DATE));
        salesData.setStoreName(fields[1]);
        salesData.setProductName(fields[2]);
        salesData.setUnits(Long.parseLong(fields[3]));
        salesData.setRevenue(new BigDecimal(fields[4]));
        salesService.save(salesData);
    }
}
