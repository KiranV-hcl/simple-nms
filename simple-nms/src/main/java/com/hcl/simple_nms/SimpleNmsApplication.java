package com.hcl.simple_nms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SimpleNmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleNmsApplication.class, args);
    }
}