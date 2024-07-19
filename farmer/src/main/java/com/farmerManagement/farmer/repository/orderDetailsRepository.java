package com.farmerManagement.farmer.repository;

import com.farmerManagement.farmer.entity.orderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface orderDetailsRepository extends JpaRepository<orderDetailsEntity, Integer> {
    @Query("SELECT v FROM orderDetailsEntity  v WHERE v.purchaseOrderId = :purchaseId")
    List<orderDetailsEntity> findByPurchaseOrderId(Integer purchaseId);

}
