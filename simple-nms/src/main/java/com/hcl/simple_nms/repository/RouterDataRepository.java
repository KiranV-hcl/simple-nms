package com.hcl.simple_nms.repository;

import com.hcl.simple_nms.model.RouterDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouterDataRepository extends JpaRepository<RouterDataEntity, Long> {
}