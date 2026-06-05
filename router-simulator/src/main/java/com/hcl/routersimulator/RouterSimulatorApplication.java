package com.hcl.routersimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RouterSimulatorApplication {

	public static void main(String[] args) {
		System.out.println("This is added to test the starting point..!");
		SpringApplication.run(RouterSimulatorApplication.class, args);
	}

}
