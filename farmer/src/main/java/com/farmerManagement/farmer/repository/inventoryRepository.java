package com.farmerManagement.farmer.repository;

import com.farmerManagement.farmer.entity.inventoryEntity;
import com.farmerManagement.farmer.entity.sellersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface inventoryRepository extends JpaRepository<inventoryEntity, Integer> {
    @Query("select v from  inventoryEntity v where v.inventoryName = :name")
    List<inventoryEntity> findInventoryDetailsByName(@Param("name") String name);
}
