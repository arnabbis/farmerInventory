package com.farmerManagement.farmer.repository;

import com.farmerManagement.farmer.entity.sellersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface sellerRepository extends JpaRepository<sellersEntity, Integer> {
    @Query("SELECT v FROM sellersEntity  v WHERE v.sellerMobileNumber = :mobile")
    List<sellersEntity> findByMobile(@Param("mobile") String mobile);
}
