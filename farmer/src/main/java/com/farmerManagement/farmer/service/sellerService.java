package com.farmerManagement.farmer.service;

import com.farmerManagement.farmer.entity.sellersEntity;

import java.util.List;
import java.util.Optional;

public interface sellerService {
    List<sellersEntity> findAllSellers();
    Optional<sellersEntity> findSellerById(int id);
    sellersEntity saveSeller(sellersEntity seller);
    sellersEntity updateSeller(sellersEntity seller);
    boolean deleteSeller(int id);
    List<sellersEntity> findByMobile (String mobile);

    List<sellersEntity> getAllSellers();
}
