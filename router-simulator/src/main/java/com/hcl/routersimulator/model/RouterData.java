package com.hcl.routersimulator.model;

public class RouterData {

    private String routerId;
    private String ipAddress;
    private String status;
    private double cpuUsage;
    private double memoryUsage;
    private long timestamp;

    // ✅ No-args constructor
    public RouterData() {
    }

    // ✅ All-args constructor
    public RouterData(String routerId, String ipAddress, String status,
                      double cpuUsage, double memoryUsage, long timestamp) {
        this.routerId = routerId;
        this.ipAddress = ipAddress;
        this.status = status;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.timestamp = timestamp;
    }

    // ✅ Getters & Setters

    public String getRouterId() {
        return routerId;
    }

    public void setRouterId(String routerId) {
        this.routerId = routerId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public double getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    // ✅ toString (useful for logging)
    @Override
    public String toString() {
        return "RouterData{" +
                "routerId='" + routerId + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", status='" + status + '\'' +
                ", cpuUsage=" + cpuUsage +
                ", memoryUsage=" + memoryUsage +
                ", timestamp=" + timestamp +
                '}';
    }
}