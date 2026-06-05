package com.hcl.simple_nms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.simple_nms.model.RouterData;
import com.hcl.simple_nms.model.RouterDataEntity;
import com.hcl.simple_nms.repository.RouterDataRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RouterConsumerService {

	private final ObjectMapper objectMapper = new ObjectMapper();
//	private final FileWriterService fileWriterService;
	
	private final RouterDataRepository repository;
	
//	public RouterConsumerService(FileWriterService fileWriterService) {
//		this.fileWriterService = fileWriterService;
//	}
//	

	public RouterConsumerService(RouterDataRepository repository) {
		this.repository = repository;
	}


	@KafkaListener(topics = "router-topic", groupId = "nms-group")
	public void consume(String message) {

//        try {
//            RouterData data = objectMapper.readValue(message, RouterData.class);
//
//            System.out.println(" Received: " + data.getRouterId());
//            
//            
//
////            fileWriterService.writeToFile(message);
//
//            process(data);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

		try {
			RouterData data = objectMapper.readValue(message, RouterData.class);

			System.out.println(" Received: " + data.getRouterId());

			// Save to DB
			RouterDataEntity entity = new RouterDataEntity();

			entity.setRouterId(data.getRouterId());
			entity.setIpAddress(data.getIpAddress());
			entity.setStatus(data.getStatus());
			entity.setCpuUsage(data.getCpuUsage());
			entity.setMemoryUsage(data.getMemoryUsage());
			entity.setTimestamp(data.getTimestamp());

			repository.save(entity);

			process(data);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void process(RouterData data) {

		if ("DOWN".equalsIgnoreCase(data.getStatus())) {
			System.out.println(" ALERT: Router DOWN → " + data.getRouterId());
		}

		if (data.getCpuUsage() > 80) {
			System.out.println(" HIGH CPU → " + data.getRouterId() + " CPU: " + data.getCpuUsage());
		}
	}
}