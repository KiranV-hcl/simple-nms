package com.hcl.simple_nms.exception;

import org.springframework.web.bind.annotation.*;

import com.hcl.simple_nms.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// To handle custom exception
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        return "Error: " + ex.getMessage();
    }
    
    // To throw custom exception
    @ExceptionHandler(DeviceNotFoundException.class)
    public ApiResponse<String> handleDeviceNotFound(DeviceNotFoundException ex) {

        return new ApiResponse<>(ex.getMessage(), null);
    }

}