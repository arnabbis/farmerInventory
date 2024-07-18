package com.farmerManagement.farmer.repository;

import com.farmerManagement.farmer.entity.purchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface purchaseOrderRepository extends JpaRepository<purchaseOrder, Integer> {
    @Modifying
    @Query("UPDATE purchaseOrder v SET v.totalPrice = :price WHERE v.id = :id")
    void updateTotalPrice(@Param("id") Integer id, @Param("price") double price);
}
