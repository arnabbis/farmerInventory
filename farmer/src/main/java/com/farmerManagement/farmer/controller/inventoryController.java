package com.farmerManagement.farmer.controller;

import com.farmerManagement.farmer.entity.inventoryEntity;
import com.farmerManagement.farmer.respose.response;
import com.farmerManagement.farmer.service.inventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class inventoryController {
    public final inventoryService service;

    public inventoryController(inventoryService service) {
        this.service = service;
    }

    @GetMapping("/getAllInventoryProducts")
    public ResponseEntity<response<List<inventoryEntity>>> getInventory() {
        try {
            List<inventoryEntity> getAllData = service.findAll();
            response<List<inventoryEntity>> data = new response<>(HttpStatus.OK.value(), "All inventory products found", getAllData);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            response<List<inventoryEntity>> error = new response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error" + e.getMessage(), null);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findProductById/{id}")
    public ResponseEntity<response<Optional<inventoryEntity>>> findProductById(@PathVariable("id") int id) {
        Optional<inventoryEntity> data = service.findById(id);
        response<Optional<inventoryEntity>> data1;
        if (data.isPresent()) {
            data1 = new response<>(HttpStatus.OK.value(), "Fetched the product from Inventory", data);
        } else {
            data1 = new response<>(HttpStatus.NOT_FOUND.value(), "No product found", data);
        }
        return new ResponseEntity<>(data1, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<response<inventoryEntity>> createInventoryProduct(@RequestBody inventoryEntity inventory) {
        try {
            inventoryEntity createData = service.saveProduct(inventory);
            response<inventoryEntity> result = new response<>(HttpStatus.CREATED.value(), "Product created successfully", createData);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            response<inventoryEntity> error = new response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error" + e.getMessage(), null);
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<response<inventoryEntity>> updateInventoryProduct(@PathVariable("id") int id, @RequestBody inventoryEntity inventory) {
        Optional<inventoryEntity> findId = service.findById(id);
        System.out.println(findId);
        if (!findId.isPresent()) {
            response<inventoryEntity> response = new response<>(HttpStatus.NOT_FOUND.value(), "Product not found with ID: " + id, null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        inventoryEntity updateProduct = findId.get();
        System.out.println(updateProduct);
        if (inventory.getProductName() != null) {
            updateProduct.setProductName(inventory.getProductName());
        }
        if (inventory.getProductDescription() != null) {
            updateProduct.setProductDescription(inventory.getProductDescription());
        }
        if (inventory.getProductPrice() != null) {
            updateProduct.setProductName(inventory.getProductName());
        }
        updateProduct = service.updateProduct(id, inventory);
        response<inventoryEntity> response = new response<>(HttpStatus.OK.value(), "Product updated successfully", updateProduct);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<response<Void>> deleteInventoryProduct(@PathVariable("id") int id) {
        boolean isDeleted = service.deleteProduct(id);
        if (isDeleted) {
            response<Void> response = new response<>(HttpStatus.OK.value(), "Product deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response<Void> response = new response<>(HttpStatus.NOT_FOUND.value(), "Product not found with ID: " + id, null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
