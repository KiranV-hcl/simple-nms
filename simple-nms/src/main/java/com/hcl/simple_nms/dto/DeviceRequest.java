package com.hcl.simple_nms.dto;

import java.util.List;

public class DeviceRequest {

    private List<String> deviceIds;

    // getters & setters

    public List<String> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }
}