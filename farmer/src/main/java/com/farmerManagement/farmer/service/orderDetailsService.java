package com.farmerManagement.farmer.service;

import com.farmerManagement.farmer.entity.orderDetailsEntity;

import java.util.List;
import java.util.Optional;

public interface orderDetailsService {
        List<orderDetailsEntity> findAll();
        Optional<orderDetailsEntity> findById(int id);
        orderDetailsEntity save(orderDetailsEntity orderDetailsEntity);
        orderDetailsEntity update(orderDetailsEntity orderDetailsEntity);
        boolean delete(int id);
        List<orderDetailsEntity> findByPurchaseOrderId(int purchaseOrderId);
}
