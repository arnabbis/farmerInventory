package com.farmerManagement.farmer.controller;


import com.farmerManagement.farmer.entity.inventoryEntity;
import com.farmerManagement.farmer.entity.vegetableEntity;
import com.farmerManagement.farmer.respose.response;
import com.farmerManagement.farmer.service.vegetableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vegetables")
public class vegetableController {
    public final vegetableService vegetableService;
    public vegetableController(vegetableService vegetableService) {
        this.vegetableService = vegetableService;
    }

    @GetMapping("/getAllVegetables")
        public ResponseEntity<response<List<vegetableEntity>>> getAllVegetables() {
        try {
            List<vegetableEntity> vegetables = vegetableService.getAllVegetables();
            response<List<vegetableEntity>> response = new response<>(HttpStatus.OK.value(), "All vegetables", vegetables);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response<List<vegetableEntity>> response = new response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error getting vegetables", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/getVegetableById/{id}")
    public ResponseEntity<response<Optional<vegetableEntity>>> getVegetableById(@PathVariable int id){
        try {
           Optional<vegetableEntity> getData = vegetableService.getVegetableById(id);
           response<Optional<vegetableEntity>> getResponse;
           if(getData.isPresent()){
               getResponse = new response<>(HttpStatus.OK.value(), "Vegetable Found Successfully", getData);
           }else {
               getResponse = new response<>(HttpStatus.NOT_FOUND.value(), "Vegetable Found Successfully", null);

           }
           return new ResponseEntity<>(getResponse, HttpStatus.OK);
        }catch (Exception e){
            response<Optional<vegetableEntity>> response = new response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error getting vegetables", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PostMapping("/createVegetable")
    public ResponseEntity<response<vegetableEntity>> createVegetable(@RequestBody vegetableEntity vegetableEntity) {
        String vegetableName = vegetableEntity.getVegetableName().toLowerCase();
        List<vegetableEntity> foundVegetables = vegetableService.getVegetableByName(vegetableName);

        if (!foundVegetables.isEmpty()) {
            response<vegetableEntity> getResponse = new response<>(
                    HttpStatus.CONFLICT.value(), // 409 Conflict indicates that the request could not be completed due to a conflict with the current state of the resource.
                    "Vegetable already exists",
                    foundVegetables.get(0) // You can return the first found vegetable
            );
            return new ResponseEntity<>(getResponse, HttpStatus.CONFLICT);
        } else {
            vegetableEntity savedVegetable = vegetableService.addVegetable(vegetableEntity);

            response<vegetableEntity> getResponse = new response<>(
                    HttpStatus.CREATED.value(), // 201 Created indicates that the request has been fulfilled and resulted in a new resource being created.
                    "Vegetable created successfully",
                    savedVegetable
            );
            return new ResponseEntity<>(getResponse, HttpStatus.CREATED);
        }
    }


    @PutMapping ("/updateVegetable/{id}")
    public  ResponseEntity<response<vegetableEntity>> updateVegetable(@PathVariable int id, @RequestBody vegetableEntity vegetableEntity){
        Optional<vegetableEntity> findById = vegetableService.getVegetableById(id);
        if(!findById.isPresent()){
            response<vegetableEntity> getresponse = new response<>(
                    HttpStatus.NOT_FOUND.value(), // 409 Conflict indicates that the request could not be completed due to a conflict with the current state of the resource.
                    "Vegetable doesnot already exists",
                    null
            );
            return new ResponseEntity<>(getresponse, HttpStatus.NOT_FOUND);
        }
        vegetableEntity updateProduct = findById.get();
        if (vegetableEntity.getVegetableName() != null) {
            updateProduct.setVegetableName(vegetableEntity.getVegetableName());
        }
        if (vegetableEntity.getVegetablePrice() != null) {
            updateProduct.setVegetablePrice(vegetableEntity.getVegetablePrice());
        }
        if (vegetableEntity.getVegetableQuantity() != null) {
            updateProduct.setVegetableQuantity(vegetableEntity.getVegetableQuantity());
        }
        if (vegetableEntity.getPerUnitPrice() != null) {
            updateProduct.setPerUnitPrice(vegetableEntity.getPerUnitPrice());
        }

        vegetableEntity updatedVegetable = vegetableService.updateVegetable(updateProduct);
        response<vegetableEntity> response = new response<>(HttpStatus.OK.value(), "Product updated successfully", updatedVegetable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<response<Void>> deleteById(@PathVariable int id) {
        Optional<vegetableEntity> findById = vegetableService.getVegetableById(id);
        response<Void> response;
        if (!findById.isPresent()) {
            response<Void> data = new response<>(HttpStatus.NOT_FOUND.value(), "Vegetable doesnot exists", null);
            return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
        }
        boolean deleteById = vegetableService.deleteVegetable(id);
        if (deleteById) {
            response = new response<>(HttpStatus.OK.value(), "Vegetable deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
