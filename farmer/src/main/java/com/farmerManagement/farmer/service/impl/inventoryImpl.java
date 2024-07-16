package com.farmerManagement.farmer.service.impl;

import com.farmerManagement.farmer.entity.inventoryEntity;
import com.farmerManagement.farmer.repository.inventoryRepository;
import com.farmerManagement.farmer.service.inventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class inventoryImpl implements inventoryService {

    private final inventoryRepository inventoryRepo;

    public inventoryImpl(inventoryRepository inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    @Override
    public List<inventoryEntity> findAll() {
        return inventoryRepo.findAll();
    }

    @Override
    public Optional<inventoryEntity> findById(int id) {
        return inventoryRepo.findById(id);
    }

    @Override
    public inventoryEntity saveProduct(inventoryEntity inventoryEntity) {
        System.out.println(inventoryEntity);
        return inventoryRepo.save(inventoryEntity);
    }

    @Override
    public inventoryEntity updateProduct(inventoryEntity inventoryEntity) {
        return inventoryRepo.save(inventoryEntity);
    }

    @Override
    public List<inventoryEntity> findByName(String name) {
        return inventoryRepo.findInventoryDetailsByName(name);
    }

    @Override
    public boolean deleteProduct(int id) {
        Optional<inventoryEntity> product = inventoryRepo.findById(id);
        if (product.isPresent()) {
            inventoryRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
