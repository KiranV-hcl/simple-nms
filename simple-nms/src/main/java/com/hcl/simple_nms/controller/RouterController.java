package com.hcl.simple_nms.controller;

import com.hcl.simple_nms.dto.ApiResponse;
import com.hcl.simple_nms.dto.DeviceMultiRequest;
import com.hcl.simple_nms.dto.DeviceRequest;
import com.hcl.simple_nms.dto.DeviceResponse;
import com.hcl.simple_nms.dto.DeviceSingleRequest;
import com.hcl.simple_nms.dto.MetricsDTO;
import com.hcl.simple_nms.exception.DeviceNotFoundException;
import com.hcl.simple_nms.model.RouterDataEntity;
import com.hcl.simple_nms.repository.RouterDataRepository;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@RestController
@RequestMapping("/routers")
public class RouterController {

	private final RouterDataRepository repository;

	public RouterController(RouterDataRepository repository) {
		this.repository = repository;
	}

//    //API: /getDeviceStatus
//    @PostMapping("/getDeviceStatus")
//    public List<RouterDataEntity> getDeviceStatus(@RequestBody DeviceRequest request) {
//
//        return repository.findByDeviceIdIn(request.getDeviceIds());
//    }

//	@PostMapping("/getDeviceStatus")
//	public ApiResponse<List<DeviceResponse>> getDeviceStatus(@RequestBody DeviceRequest request) {
//
//	    List<DeviceResponse> response = repository
//	            .findByDeviceIdIn(request.getDeviceIds())
//	            .stream()
//	            .map(e -> new DeviceResponse(
//	                    e.getDeviceId(),
//	                    e.getTemperature(),
//	                    e.getNumPackets(),
//	                    e.isAlive()))
//	            .toList();
//
//	    // ✅ Throw exception if no data found
//	    if (response.isEmpty()) {
//	        throw new DeviceNotFoundException("No devices found..!");
//	    }
//
//	    return new ApiResponse<>("Success ", response);
//	}
	
	@PostMapping("/getDeviceStatus")
	@Operation(summary = "Get status of a single device")
	public ApiResponse<MetricsDTO> getDeviceStatus(@RequestBody DeviceSingleRequest request) {

	    if (request.getDeviceId() == null || request.getDeviceId().isEmpty()) {
	        throw new IllegalArgumentException("DeviceId cannot be null or empty ❌");
	    }

	    // Fetch from DB (throw exception if not found)
	    RouterDataEntity entity = repository
	            .findByDeviceId(request.getDeviceId())
	            .stream()
	            .findFirst()
	            .orElseThrow(() -> new DeviceNotFoundException("Device not found ❌"));

	    // Map to DTO
	    MetricsDTO metrics = new MetricsDTO(
	            entity.getDeviceId(),
	            entity.getTemperature(),
	            entity.getNumPackets(),
	            entity.isAlive()
	    );

	    return new ApiResponse<>("Success ", metrics);
	}
	
	@PostMapping("/getDeviceStatusMultiple")
	@Operation(summary = "Get status of multiple devices with pagination")
	public ApiResponse<List<MetricsDTO>> getDeviceStatusMultiple(
	        @RequestBody DeviceMultiRequest request) {

	    // Validate input
	    if (request.getDeviceIds() == null || request.getDeviceIds().isEmpty()) {
	        throw new IllegalArgumentException("Device list cannot be empty ❌");
	    }

	    if (request.getSize() <= 0) {
	        throw new IllegalArgumentException("Page size must be greater than 0 ❌");
	    }

	    // Fetch paged data from DB
	    Page<RouterDataEntity> page = repository.findByDeviceIdIn(
	            request.getDeviceIds(),
	            PageRequest.of(request.getPage(), request.getSize())
	    );

	    // Convert to DTO
	    List<MetricsDTO> metricsList = page.getContent()
	            .stream()
	            .map(e -> new MetricsDTO(
	                    e.getDeviceId(),
	                    e.getTemperature(),
	                    e.getNumPackets(),
	                    e.isAlive()
	            ))
	            .toList();

	    // If no data found
	    if (metricsList.isEmpty()) {
	        throw new DeviceNotFoundException("No devices found ❌");
	    }

	    return new ApiResponse<>("Success ", metricsList);
	}

//	@GetMapping("/getStatus")
//	public Page<RouterDataEntity> getStatus(@RequestParam int page, @RequestParam int size) {
//
//		return repository.findAll(PageRequest.of(page, size));
//	}
}