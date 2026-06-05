package com.hcl.routersimulator.simulator;

import com.hcl.routersimulator.model.RouterData;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class RouterDataGenerator {

    private final Random random = new Random();

    public RouterData generate() {

        RouterData data = new RouterData();

        data.setRouterId("R-" + UUID.randomUUID().toString().substring(0, 5));
        data.setIpAddress("192.168.1." + random.nextInt(255));
        data.setStatus(random.nextBoolean() ? "UP" : "DOWN");
        data.setCpuUsage(10 + (90 * random.nextDouble()));
        data.setMemoryUsage(20 + (80 * random.nextDouble()));
        data.setTimestamp(System.currentTimeMillis());

        return data;
    }
}