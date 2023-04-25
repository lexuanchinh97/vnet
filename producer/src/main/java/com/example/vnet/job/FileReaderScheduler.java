package com.example.vnet.job;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileReaderScheduler {

    @Value("${sales.file.path}")
    private String salesFilePath;

    @Value("${sales.file.archive}")
    private String archivePath;

    @Value("${spring.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedRate = 60000)
    private void readSalesFiles() {
        File folder = new File(salesFilePath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line = br.readLine();
                        while ((line = br.readLine()) != null) {
                            kafkaTemplate.send(topic, line);
                        }
                        br.close();
                        archiveFile(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void archiveFile(File file) {
        Path sourcePath = Paths.get(file.getAbsolutePath());
        Path destinationPath = Paths.get(archivePath, file.getName());
        try {
            Files.move(sourcePath, destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
