package com.farmerManagement.farmer.service.impl;

import com.farmerManagement.farmer.entity.inventoryEntity;
import com.farmerManagement.farmer.entity.sellersEntity;
import com.farmerManagement.farmer.repository.sellerRepository;
import com.farmerManagement.farmer.service.sellerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class serverImpl implements sellerService {

    private final sellerRepository sellerRepository;

    public serverImpl(com.farmerManagement.farmer.repository.sellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }


    @Override
    public List<sellersEntity> getAllSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public List<sellersEntity> findAllSellers() {
        return List.of();
    }

    @Override
    public Optional<sellersEntity> findSellerById(int id) {
        return sellerRepository.findById(id);
    }

    @Override
    public sellersEntity saveSeller(sellersEntity sellersEntity) {
        System.out.println(sellersEntity);
        return sellerRepository.save(sellersEntity);
    }

    @Override
    public sellersEntity updateSeller(sellersEntity sellersEntity) {
        return sellerRepository.save(sellersEntity);
    }

    @Override
    public boolean deleteSeller(int id) {
        Optional<sellersEntity> product = sellerRepository.findById(id);
        if (product.isPresent()) {
            sellerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<sellersEntity> findByMobile(String mobile) {
        return sellerRepository.findByMobile(mobile);
    }

}
