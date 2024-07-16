package com.farmerManagement.farmer.controller;

import com.farmerManagement.farmer.entity.inventoryEntity;
import com.farmerManagement.farmer.entity.vegetableEntity;
import com.farmerManagement.farmer.respose.response;
import com.farmerManagement.farmer.service.inventoryService;
import com.farmerManagement.farmer.service.vegetableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/inventory")
public class inventoryController {
    public final inventoryService service;
    public  final vegetableService vegetableservice;

    public inventoryController(inventoryService service, vegetableService vegetableservice) {
        this.service = service;
        this.vegetableservice = vegetableservice;
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
            int getId = inventory.getVegetableId();
            Optional<vegetableEntity> vegetable = vegetableservice.getVegetableById(getId);
            if(vegetable.isEmpty()) {
                response<inventoryEntity> error = new response<>(HttpStatus.NOT_FOUND.value(), "No vegetable found", null);
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            if(inventory.getDate()==null){
                inventory.setDate(new Date());
            }
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
        if (findId.isEmpty()) {
            response<inventoryEntity> response = new response<>(HttpStatus.NOT_FOUND.value(), "Product not found with ID: " + id, null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        inventoryEntity updateProduct = findId.get();
        if (inventory.getInventoryName() != null) {
            updateProduct.setInventoryName(inventory.getInventoryName());
        }
        if (inventory.getInventoryLocation() != null) {
            updateProduct.setInventoryLocation(inventory.getInventoryLocation());
        }
        if (inventory.getVegetableId() != null) {
            if(vegetableservice.getVegetableById(inventory.getInventoryId()).isPresent()) {
                updateProduct.setInventoryId(inventory.getVegetableId());
            }
        }
        inventoryEntity updateProducts = service.updateProduct(inventory);
        response<inventoryEntity> response = new response<>(HttpStatus.OK.value(), "Product updated successfully", updateProducts);
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

    @GetMapping("/getAllVegetablesByName")
    public  ResponseEntity<response<List<Map<String, Object>>>> getAllVegetablesByName(@RequestParam String name) {
        List<inventoryEntity> getAllData = service.findByName(name);
        List<Map<String, Object>> result = new ArrayList<>();
        for (inventoryEntity inventory : getAllData) {
            Optional<vegetableEntity> vegetableOpt = vegetableservice.getVegetableById(inventory.getVegetableId());
            Map<String, Object> inventoryMap = new HashMap<>();
            inventoryMap.put("inventoryId", inventory.getInventoryId());
            inventoryMap.put("inventoryName", inventory.getInventoryName());
            inventoryMap.put("inventoryLocation", inventory.getInventoryLocation());
            inventoryMap.put("date", inventory.getDate());
            if (vegetableOpt.isPresent()) {
                vegetableEntity vegetable = vegetableOpt.get();
                // Add vegetable data to the result map
                Map<String, Object> vegetableMap = new HashMap<>();
                vegetableMap.put("vegetableId", vegetable.getId());
                vegetableMap.put("vegetableName", vegetable.getVegetableName());
                vegetableMap.put("vegetablePrice", vegetable.getVegetablePrice());
                vegetableMap.put("vegetableQuantity", vegetable.getVegetableQuantity());
                vegetableMap.put("vegetablePerUnitPrice",vegetable.getPerUnitPrice());
                inventoryMap.put("vegetable", vegetableMap);
            } else {
                inventoryMap.put("vegetable", null);
            }

            result.add(inventoryMap);
        }
        response<List<Map<String, Object>>> data = new response<>(HttpStatus.OK.value(), "All inventory products found", result);
        return new ResponseEntity<>(data, HttpStatus.OK);

    }

    private static Map<String, Object> getStringObjectMap(Optional<vegetableEntity> vegetableOpt) {
        vegetableEntity vegetable = vegetableOpt.get();
        // Add vegetable data to the result map
        Map<String, Object> vegetableMap = new HashMap<>();
        vegetableMap.put("vegetableId", vegetable.getId());
        vegetableMap.put("vegetableName", vegetable.getVegetableName());
        vegetableMap.put("vegetablePrice", vegetable.getVegetablePrice());
        vegetableMap.put("vegetableQuantity", vegetable.getVegetableQuantity());
        vegetableMap.put("vegetablePerUnitPrice", vegetable.getPerUnitPrice());
        return vegetableMap;
    }
}
