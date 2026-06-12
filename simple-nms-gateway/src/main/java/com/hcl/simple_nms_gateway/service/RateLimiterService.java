package com.hcl.simple_nms_gateway.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    private static final int MAX_REQUESTS = 5;     // limit
    private static final long WINDOW_SIZE = 60000; // 1 minute

    private final Map<String, RequestInfo> requestMap = new ConcurrentHashMap<>();

    public boolean isAllowed(String key) {

        long currentTime = System.currentTimeMillis();

        requestMap.putIfAbsent(key, new RequestInfo(0, currentTime));

        RequestInfo info = requestMap.get(key);

        // reset window
        if (currentTime - info.timestamp > WINDOW_SIZE) {
            info.count = 0;
            info.timestamp = currentTime;
        }

        info.count++;

        return info.count <= MAX_REQUESTS;
    }

    static class RequestInfo {
        int count;
        long timestamp;

        RequestInfo(int count, long timestamp) {
            this.count = count;
            this.timestamp = timestamp;
        }
    }
}