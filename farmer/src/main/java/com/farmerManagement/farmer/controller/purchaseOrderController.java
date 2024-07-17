package com.farmerManagement.farmer.controller;


import com.farmerManagement.farmer.entity.purchaseOrder;
import com.farmerManagement.farmer.entity.sellersEntity;
import com.farmerManagement.farmer.respose.response;
import com.farmerManagement.farmer.service.orderDetailsService;
import com.farmerManagement.farmer.service.purchaseOrderService;
import com.farmerManagement.farmer.service.sellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchaseOrder")
public class purchaseOrderController {
    public final purchaseOrderService service;
    public final sellerService seller;
    private static final Logger logger = LoggerFactory.getLogger(purchaseOrderController.class);

    public purchaseOrderController(purchaseOrderService service, sellerService seller) {
        this.service = service;
        this.seller = seller;
    }

    @GetMapping("/getAllPurchaseOrder")
    public ResponseEntity<response<List<purchaseOrder>>> getAllPurchaseOrder() {
        List<purchaseOrder> list = service.findAll();
        response<List<purchaseOrder>> response = new response<>(HttpStatus.OK.value(), "All purchaseOrder Found Successfully",list);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/getPurchaseOrderById/{id}")
    public ResponseEntity<response<Optional<purchaseOrder>>> getPurchaseOrderById(@PathVariable("id") int id){
        Optional<purchaseOrder> purchaseOrder = service.findById(id);
        response<Optional<purchaseOrder>> getResponse;
        if(purchaseOrder.isPresent()){
            getResponse = new response<>(HttpStatus.OK.value(), "Purchase Order Found Successfully",purchaseOrder);
        }else {
            getResponse = new response<>(HttpStatus.NOT_FOUND.value(), "Purchase Order Not Found",null);
        }

        return new ResponseEntity<>(getResponse,HttpStatus.OK);
    }

    @PostMapping("/createPurchaseOrder")
    public ResponseEntity<response<purchaseOrder>> createPurchaseOrder(@RequestBody purchaseOrder purchaseOrder){
            Optional<sellersEntity> findSellerById = seller.findSellerById(purchaseOrder.getSellerId());
            if(findSellerById.isEmpty()){
                response<purchaseOrder> createPurchaseOrderResponse = new response<>(HttpStatus.NOT_FOUND.value(), "No Such Seller",null);
                return new ResponseEntity<>(createPurchaseOrderResponse,HttpStatus.NOT_FOUND);
            }
        if (purchaseOrder.getContactInfo() == null) {
            sellersEntity seller = findSellerById.get();
            logger.info("Creating Purchase Order Successfully");
            logger.info(String.valueOf(seller));
            purchaseOrder.setContactInfo(seller.getSellerMobileNumber());
        }

        if(purchaseOrder.getOrderDate()==null){
                purchaseOrder.setOrderDate(new Date());
            }
        purchaseOrder createOrder = service.save(purchaseOrder);
            response<purchaseOrder> createResponse = new response<>(HttpStatus.CREATED.value(), "Purchase Order Created",createOrder);
            return new ResponseEntity<>(createResponse,HttpStatus.CREATED);
    }

    @DeleteMapping("/deletePurchaseOrder")
    public ResponseEntity<response<Void>> deletePurchaseOrder(@PathVariable("id") int id ){
        Optional<purchaseOrder> findPurchaseOrder = service.findById(id);
        response<Void> deleteResponse = new response<>();
        if(findPurchaseOrder.isEmpty()){
            response<Void> response = new response<>(HttpStatus.NOT_FOUND.value(), "Purchase Order Not Found",null);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        boolean deleted = service.deleteById(id);
        if(deleted){
            deleteResponse = new response<>(HttpStatus.OK.value(), "Purchase Order Deleted",null);
        }

        return new ResponseEntity<>(deleteResponse,HttpStatus.OK);
    }
}
