package com.farmerManagement.farmer.repository;

import com.farmerManagement.farmer.entity.inventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface inventoryRepository extends JpaRepository<inventoryEntity, Integer> {
}
