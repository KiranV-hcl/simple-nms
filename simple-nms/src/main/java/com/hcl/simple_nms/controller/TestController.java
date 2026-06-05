package com.hcl.simple_nms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // ✅ Basic health check endpoint
    @GetMapping("/")
    public String home() {
        return "Simple NMS is running ✅";
    }

    // ✅ Optional: additional test endpoint
    @GetMapping("/status")
    public String status() {
        return "Service is UP 🚀";
    }
}