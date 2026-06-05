package com.hcl.routersimulator.controller;

import com.hcl.routersimulator.model.RouterData;
import com.hcl.routersimulator.service.RouterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouterController {

    private final RouterService routerService;

    public RouterController(RouterService routerService) {
        this.routerService = routerService;
    }

    @GetMapping("/router/data")
    public RouterData getRouterData() {
        return routerService.getRouterData();
    }
}