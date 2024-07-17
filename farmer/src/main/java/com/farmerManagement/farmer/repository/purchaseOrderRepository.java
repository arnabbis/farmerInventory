package com.farmerManagement.farmer.repository;

import com.farmerManagement.farmer.entity.purchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface purchaseOrderRepository extends JpaRepository<purchaseOrder, Integer> {
}
