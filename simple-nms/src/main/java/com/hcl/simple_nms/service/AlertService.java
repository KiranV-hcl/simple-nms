package com.hcl.simple_nms.service;

import com.hcl.simple_nms.model.RouterData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    // Inject from properties
    @Value("${alert.temperature.threshold}")
    private double temperatureThreshold;

    @Value("${alert.packet.threshold}")
    private long packetThreshold;

    /**
     * Main processing method
     */
    public void process(RouterData data) {

        if (data == null) {
            System.out.println("Received null data");
            return;
        }

        checkDeviceHealth(data);
        checkTemperature(data);
        checkTraffic(data);
    }

    /**
     *  Device DOWN Alert
     */
    private void checkDeviceHealth(RouterData data) {
        if (!data.isAlive()) {
            System.out.println("ALERT: Device DOWN → " + data.getDeviceId());
        }
    }

    /**
     *  High Temperature Alert
     */
    private void checkTemperature(RouterData data) {
        if (data.getTemperature() > temperatureThreshold) {
            System.out.println("HIGH TEMP → Device: "
                    + data.getDeviceId()
                    + " | Temp: " + data.getTemperature());
        }
    }

    /**
     *  High Traffic Alert
     */
    private void checkTraffic(RouterData data) {
        if (data.getNumPackets() > packetThreshold) {
            System.out.println("HIGH TRAFFIC → Device: "
                    + data.getDeviceId()
                    + " | Packets: " + data.getNumPackets());
        }
    }
}