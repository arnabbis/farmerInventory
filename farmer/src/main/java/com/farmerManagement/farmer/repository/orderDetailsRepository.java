package com.farmerManagement.farmer.repository;

import com.farmerManagement.farmer.entity.orderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface orderDetailsRepository extends JpaRepository<orderDetailsEntity, Integer> {

}
