package com.farmerManagement.farmer.repository;

import com.farmerManagement.farmer.entity.invoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface invoiceRepository extends JpaRepository<invoiceEntity, Integer> {
}
