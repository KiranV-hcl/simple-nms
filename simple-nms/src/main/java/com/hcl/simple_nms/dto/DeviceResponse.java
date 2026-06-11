package com.hcl.simple_nms.dto;

public class DeviceResponse {

    private String deviceId;
    private double temperature;
    private long numPackets;
    private boolean alive;

    public DeviceResponse(String deviceId, double temperature, long numPackets, boolean alive) {
        this.deviceId = deviceId;
        this.temperature = temperature;
        this.numPackets = numPackets;
        this.alive = alive;
    }

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public long getNumPackets() {
		return numPackets;
	}

	public void setNumPackets(long numPackets) {
		this.numPackets = numPackets;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}