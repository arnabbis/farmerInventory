package com.farmerManagement.farmer.service.impl;

import com.farmerManagement.farmer.entity.orderDetailsEntity;
import com.farmerManagement.farmer.repository.orderDetailsRepository;
import com.farmerManagement.farmer.service.orderDetailsService;

import java.util.List;
import java.util.Optional;

public class orderDetailsImpl implements orderDetailsService {

    private final orderDetailsRepository orderDetailsRepo;

    public orderDetailsImpl(orderDetailsRepository orderDetailsRepo) {
        this.orderDetailsRepo = orderDetailsRepo;
    }

    @Override
    public List<orderDetailsEntity> findAll() {
        return orderDetailsRepo.findAll();
    }

    @Override
    public Optional<orderDetailsEntity> findById(int id) {
        return orderDetailsRepo.findById(id);
    }

    @Override
    public orderDetailsEntity save(orderDetailsEntity orderDetailsEntity) {
        return orderDetailsRepo.save(orderDetailsEntity);
    }

    @Override
    public orderDetailsEntity update(orderDetailsEntity orderDetailsEntity) {
        return orderDetailsRepo.save(orderDetailsEntity);
    }

    @Override
    public boolean delete(int id) {
        Optional<orderDetailsEntity> data = orderDetailsRepo.findById(id);
        if(data.isPresent()) {
            orderDetailsRepo.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
