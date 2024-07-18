package com.farmerManagement.farmer.service.impl;

import com.farmerManagement.farmer.entity.vegetableEntity;
import com.farmerManagement.farmer.repository.vegetableRepository;
import com.farmerManagement.farmer.service.vegetableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class vegetableImpl implements vegetableService {

    private final vegetableRepository vegetableService;

    public vegetableImpl(vegetableRepository vegetableService) {
        this.vegetableService = vegetableService;
    }

    @Override
    public List<vegetableEntity> getAllVegetables() {
        return vegetableService.findAll();
    }

    @Override
    public Optional<vegetableEntity> getVegetableById(int id) {
        return vegetableService.findById(id);
    }

    @Override
    public List<vegetableEntity> getVegetableByName(String name) {
        return vegetableService.findByName(name);
    }

    @Override
    public vegetableEntity addVegetable(vegetableEntity vegetable) {
        return vegetableService.save(vegetable);
    }

    @Override
    public vegetableEntity updateVegetable(vegetableEntity vegetable) {
        return vegetableService.save(vegetable);
    }

    @Override
    public boolean deleteVegetable(int id) {
        if(vegetableService.findById(id).isPresent()){
            vegetableService.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void updateVegetableQuantity(int id, int quantity) {
        vegetableService.updateQuantity(id,quantity);
    }
}
