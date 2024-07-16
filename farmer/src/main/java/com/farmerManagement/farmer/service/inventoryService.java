package com.farmerManagement.farmer.service;

import com.farmerManagement.farmer.entity.inventoryEntity;

import java.util.List;
import java.util.Optional;

public interface inventoryService {
    List<inventoryEntity> findAll();
    Optional<inventoryEntity> findById(int id);
    inventoryEntity saveProduct(inventoryEntity inventoryEntity);
    inventoryEntity updateProduct(inventoryEntity inventoryEntity);
    List<inventoryEntity> findByName(String name);
    boolean deleteProduct(int id);
}
