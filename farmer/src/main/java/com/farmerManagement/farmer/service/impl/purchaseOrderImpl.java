package com.farmerManagement.farmer.service.impl;

import com.farmerManagement.farmer.entity.purchaseOrder;
import com.farmerManagement.farmer.repository.purchaseOrderRepository;
import com.farmerManagement.farmer.service.purchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class purchaseOrderImpl  implements purchaseOrderService {
    private final purchaseOrderRepository purchaseOrderRepo;

    public purchaseOrderImpl(purchaseOrderRepository purchaseOrderRepo) {
        this.purchaseOrderRepo = purchaseOrderRepo;
    }


    @Override
    public List<purchaseOrder> findAll() {
        return purchaseOrderRepo.findAll();
    }

    @Override
    public Optional<purchaseOrder> findById(int id) {
        return purchaseOrderRepo.findById(id);
    }

    @Override
    public purchaseOrder save(purchaseOrder purchaseOrder) {
        return purchaseOrderRepo.save(purchaseOrder);
    }

    @Override
    public purchaseOrder update(purchaseOrder purchaseOrder) {
        return purchaseOrderRepo.save(purchaseOrder);
    }

    @Override
    public boolean deleteById(int id) {
        Optional<purchaseOrder> purchaseOrder = purchaseOrderRepo.findById(id);
        if (purchaseOrder.isPresent()) {
            purchaseOrderRepo.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
