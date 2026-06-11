package com.hcl.simple_nms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.simple_nms.model.RouterData;
import com.hcl.simple_nms.model.RouterDataEntity;
import com.hcl.simple_nms.repository.RouterDataRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RouterConsumerService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RouterDataRepository repository;
    private final AlertService alertService;

    public RouterConsumerService(RouterDataRepository repository,
                                 AlertService alertService) {
        this.repository = repository;
        this.alertService = alertService;
    }

    @KafkaListener(topics = "router-topic", groupId = "nms-group")
    public void consume(String message) {

        try {
            // Convert JSON → Object
            RouterData data = objectMapper.readValue(message, RouterData.class);

            System.out.println("📥 Received: " + data.getDeviceId());

            // Save into DB
            RouterDataEntity entity = new RouterDataEntity();

            entity.setDeviceId(data.getDeviceId());
            entity.setTemperature(data.getTemperature());
            entity.setNumPackets(data.getNumPackets());
            entity.setAlive(data.isAlive());
            entity.setTimestamp(data.getTimestamp());

            repository.save(entity);

            // Process alerts
            alertService.process(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}