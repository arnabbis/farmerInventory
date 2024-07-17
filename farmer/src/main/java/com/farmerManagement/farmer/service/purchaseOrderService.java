package com.farmerManagement.farmer.service;

import com.farmerManagement.farmer.entity.purchaseOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface purchaseOrderService {
    List<purchaseOrder> findAll();
    Optional<purchaseOrder> findById(int id);
    purchaseOrder save(purchaseOrder purchaseOrder);
    purchaseOrder update(purchaseOrder purchaseOrder);
    boolean deleteById(int id);
}
