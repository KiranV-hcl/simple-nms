package com.hcl.simple_nms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hcl.simple_nms.model.RouterDataEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RouterDataRepository extends JpaRepository<RouterDataEntity, Long> {

    List<RouterDataEntity> findByDeviceIdIn(List<String> deviceIds);
    
    List<RouterDataEntity> findByDeviceId(String deviceId);

    List<RouterDataEntity> findByAlive(boolean alive);
    
    Page<RouterDataEntity> findAll(Pageable pageable);
    
    Page<RouterDataEntity> findByDeviceIdIn(List<String> deviceIds, Pageable pageable);
}
