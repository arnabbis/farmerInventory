package com.farmerManagement.farmer.service;

import com.farmerManagement.farmer.entity.vegetableEntity;

import java.util.List;
import java.util.Optional;

public interface vegetableService {
    List<vegetableEntity> getAllVegetables();
    Optional<vegetableEntity> getVegetableById(int id);
    List<vegetableEntity> getVegetableByName(String name);
    vegetableEntity addVegetable(vegetableEntity vegetable);
    vegetableEntity updateVegetable(vegetableEntity vegetable);
    boolean deleteVegetable(int id);
}
