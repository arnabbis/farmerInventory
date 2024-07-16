package com.farmerManagement.farmer.controller;

import com.farmerManagement.farmer.entity.sellersEntity;
import com.farmerManagement.farmer.respose.response;
import com.farmerManagement.farmer.service.sellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seller")

public class sellersController {
    public final sellerService service;

    public sellersController(sellerService service) {
        this.service = service;
    }

    @GetMapping("/getAllSeller")
    public ResponseEntity<response<List<sellersEntity>>> getAllSeller (){
        List<sellersEntity> getAllSeller = service.getAllSellers();
        response<List<sellersEntity>> result = new response<>(HttpStatus.OK.value(),"Get All Seller",getAllSeller);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    @GetMapping("/getSellerById/{id}")
    public ResponseEntity<response<Optional<sellersEntity>>> getSellerById(@PathVariable int id){
        Optional<sellersEntity> checkIdPresent = service.findSellerById(id);
        if (checkIdPresent.isPresent()){
            response<Optional<sellersEntity>> response = new response<>(HttpStatus.OK.value(), "Get Seller By Id",checkIdPresent);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            response<Optional<sellersEntity>> response = new response<>(HttpStatus.NOT_FOUND.value(),"No Such Seller",null);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createSeller")
    public ResponseEntity<response<sellersEntity>> createSeller(@RequestBody sellersEntity entity){
        String getMobileNumber = entity.getSellerMobileNumber();
        List<sellersEntity> findDuplicateName = service.findByMobile(getMobileNumber);
        if(!findDuplicateName.isEmpty()){
            response<sellersEntity> response = new response<>(HttpStatus.CONFLICT.value(),"Dublicate Phone Number",findDuplicateName.get(0));
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        sellersEntity createSeller = service.saveSeller(entity);
        response<sellersEntity> response = new response<>(HttpStatus.CREATED.value(),"Created Seller",createSeller);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }


    @PutMapping("/updateSeller/{id}")
    public ResponseEntity<response<sellersEntity>> updateSeller(@PathVariable int id, @RequestBody sellersEntity entity){
        Optional<sellersEntity> findById = service.findSellerById(id);
        if (findById.isEmpty()){
            response<sellersEntity> response = new response<>(HttpStatus.NOT_FOUND.value(),"No Such Seller",null);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        sellersEntity getSeller = findById.get();
        if (entity.getSellerName() != null) {
            getSeller.setSellerName(entity.getSellerName());
        }
        if (entity.getSellerMobileNumber() != null) {
            getSeller.setSellerMobileNumber(entity.getSellerMobileNumber());
        }
        if (entity.getSellerAddress() != null) {
            getSeller.setSellerAddress(entity.getSellerAddress());
        }
        sellersEntity updateSeller = service.updateSeller(entity);
        response<sellersEntity> response = new response<>(HttpStatus.OK.value(),"Updated Seller",updateSeller);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/deleteSeller/{id}")
    public ResponseEntity<response<Void>> deleteSeller(@PathVariable int id){
        Optional<sellersEntity> findId = service.findSellerById(id);
        if (findId.isEmpty()){
            response<Void> response = new response<>(HttpStatus.NOT_FOUND.value(),"No Such Seller",null);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        service.deleteSeller(id);
        response<Void> response = new response<>(HttpStatus.OK.value(),"Deleted Seller",null);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}


