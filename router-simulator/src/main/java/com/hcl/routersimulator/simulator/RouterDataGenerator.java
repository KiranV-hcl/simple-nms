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

		data.setDeviceId("device-" + (1 + random.nextInt(10)));
		data.setTemperature(20 + (80 * random.nextDouble()));
		data.setNumPackets(500 + random.nextInt(5000));
		data.setAlive(random.nextInt(10) != 0);
		data.setTimestamp(System.currentTimeMillis());

		return data;

	}
}