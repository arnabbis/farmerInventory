package com.farmerManagement.farmer.service;

import com.farmerManagement.farmer.entity.inventoryEntity;

import java.util.List;
import java.util.Optional;

public interface inventoryService {
    List<inventoryEntity> findAll();
    Optional<inventoryEntity> findById(int id);
    inventoryEntity saveProduct(inventoryEntity inventoryEntity);
    inventoryEntity updateProduct(int id, inventoryEntity inventoryEntity);
    boolean deleteProduct(int id);
}
