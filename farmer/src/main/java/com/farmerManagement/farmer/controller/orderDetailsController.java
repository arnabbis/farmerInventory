package com.farmerManagement.farmer.controller;

import com.farmerManagement.farmer.entity.orderDetailsEntity;
import com.farmerManagement.farmer.entity.purchaseOrder;
import com.farmerManagement.farmer.entity.vegetableEntity;
import com.farmerManagement.farmer.respose.response;
import com.farmerManagement.farmer.service.orderDetailsService;
import com.farmerManagement.farmer.service.purchaseOrderService;
import com.farmerManagement.farmer.service.vegetableService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class orderDetailsController {
    public final orderDetailsService service;
    public final purchaseOrderService purchaseService;
    public final vegetableService vegetyService;

    public orderDetailsController(orderDetailsService service, purchaseOrderService purchaseService, vegetableService vegetyService) {
        this.service = service;
        this.purchaseService = purchaseService;
        this.vegetyService = vegetyService;
    }

    @GetMapping("/getAllOrderDetails")
    public ResponseEntity<response<List<orderDetailsEntity>>> getAllOrderDetails() {
        List<orderDetailsEntity> list = service.findAll();
        response<List<orderDetailsEntity>> response = new response<>(HttpStatus.OK.value(), "all order details", list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<response<Optional<orderDetailsEntity>>> getOrderById(@PathVariable int id) {
        Optional<orderDetailsEntity> list = service.findById(id);
        if (list.isEmpty()) {
            response<Optional<orderDetailsEntity>> response = new response<>(HttpStatus.NOT_FOUND.value(), "order not found", list);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Optional<orderDetailsEntity> orderDetailsEntity = service.findById(id);
        response<Optional<orderDetailsEntity>> response = new response<>(HttpStatus.OK.value(), "order details", orderDetailsEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/createOrderDetails")
    @Transactional
    public ResponseEntity<response<orderDetailsEntity>> createOrderDetails(@RequestBody orderDetailsEntity orderDetailsEntity) {
        Optional<purchaseOrder> purchaseOrder = purchaseService.findById(orderDetailsEntity.getPurchaseOrderId());
        if (purchaseOrder.isEmpty()) {
            response<orderDetailsEntity> response = new response<>(HttpStatus.NOT_FOUND.value(), "No purchase order found", null
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Optional<vegetableEntity> findVegetable = vegetyService.getVegetableById(orderDetailsEntity.getVegetableId());
        if (findVegetable.isEmpty()) {
            response<orderDetailsEntity> response = new response<>(HttpStatus.NOT_FOUND.value(), "No vegetable found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        int getQuantity = findVegetable.get().getVegetableQuantity();
        if(getQuantity <= 0 || orderDetailsEntity.getQuantity()>getQuantity) {
            response<orderDetailsEntity> response = new response<>(HttpStatus.NOT_FOUND.value(), "Quantity is more than you are asking", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if(orderDetailsEntity.getOrderDate()==null){
            orderDetailsEntity.setOrderDate(new Date());
        }
        double getNewPrice = findVegetable.get().getVegetablePrice() * orderDetailsEntity.getQuantity();
            orderDetailsEntity.setTotalPrice(getNewPrice);
        orderDetailsEntity createOrder = service.save(orderDetailsEntity);
        int storeNewQuantity = getQuantity - orderDetailsEntity.getQuantity();
        vegetyService.updateVegetableQuantity(findVegetable.get().getId(),storeNewQuantity);
        purchaseService.updatePrice(purchaseOrder.get().getId(),getNewPrice);
        if(orderDetailsEntity.getTotalPrice()==null){
            orderDetailsEntity.setTotalPrice(getNewPrice);
        }
        response<orderDetailsEntity> response = new response<>(HttpStatus.OK.value(), "order details", createOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
