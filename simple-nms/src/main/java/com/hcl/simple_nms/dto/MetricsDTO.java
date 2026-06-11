package com.hcl.simple_nms.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Device metrics response")
public class MetricsDTO {

    private String deviceId;
    private double temp;
    private long numPackets;
    private boolean health;

    public MetricsDTO(String deviceId, double temp, long numPackets, boolean health) {
        this.deviceId = deviceId;
        this.temp = temp;
        this.numPackets = numPackets;
        this.health = health;
    }

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public long getNumPackets() {
		return numPackets;
	}

	public void setNumPackets(long numPackets) {
		this.numPackets = numPackets;
	}

	public boolean isHealth() {
		return health;
	}

	public void setHealth(boolean health) {
		this.health = health;
	}
}