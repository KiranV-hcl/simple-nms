package com.hcl.routersimulator.service;

import com.hcl.routersimulator.model.RouterData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RouterScheduler {

    private final RouterService routerService;
    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Inject device count from properties
    @Value("${device.count}")
    private int deviceCount;    

    public RouterScheduler(RouterService routerService,
                           KafkaProducerService kafkaProducerService) {
        this.routerService = routerService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Scheduled(fixedRateString = "${scheduler.rate}")  
    public void generateData() {

        for (int i = 0; i < deviceCount; i++) {

            try {
                RouterData data = routerService.getRouterData();

                // Convert to JSON
                String json = objectMapper.writeValueAsString(data);

                kafkaProducerService.sendMessage(json);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}