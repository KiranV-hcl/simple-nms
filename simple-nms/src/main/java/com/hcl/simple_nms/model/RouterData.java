package com.hcl.simple_nms.model;

public class RouterData {

    private String deviceId;
    private double temperature;
    private long numPackets;
    private boolean alive;
    private long timestamp;

    // Getters & Setters

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}