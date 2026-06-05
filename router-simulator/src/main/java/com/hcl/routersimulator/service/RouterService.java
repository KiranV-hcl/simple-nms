package com.hcl.routersimulator.service;

import com.hcl.routersimulator.model.RouterData;
import com.hcl.routersimulator.simulator.RouterDataGenerator;
import org.springframework.stereotype.Service;

@Service
public class RouterService {

    private final RouterDataGenerator generator;

    public RouterService(RouterDataGenerator generator) {
        this.generator = generator;
    }

    public RouterData getRouterData() {
        return generator.generate();
    }
}