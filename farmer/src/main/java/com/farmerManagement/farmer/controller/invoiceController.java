package com.farmerManagement.farmer.controller;

import com.farmerManagement.farmer.entity.invoiceEntity;
import com.farmerManagement.farmer.entity.orderDetailsEntity;
import com.farmerManagement.farmer.entity.purchaseOrder;
import com.farmerManagement.farmer.entity.sellersEntity;
import com.farmerManagement.farmer.respose.response;
import com.farmerManagement.farmer.service.invoiceService;
import com.farmerManagement.farmer.service.orderDetailsService;
import com.farmerManagement.farmer.service.purchaseOrderService;
import com.farmerManagement.farmer.service.sellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/invoice")
public class invoiceController {
    public final invoiceService invoiceservice;
    public final sellerService sellerservice;
    public final purchaseOrderService purchaseorderservice;
    public final orderDetailsService orderdetailsservice;

    public invoiceController(invoiceService invoiceservice, sellerService sellerservice, purchaseOrderService purchaseorderservice, orderDetailsService orderdetailsservice) {
        this.invoiceservice = invoiceservice;
        this.sellerservice = sellerservice;
        this.purchaseorderservice = purchaseorderservice;
        this.orderdetailsservice = orderdetailsservice;
    }

    @GetMapping("/getAllInvoice")
    public ResponseEntity<response<List<invoiceEntity>>> getAllInvoice() {
        List<invoiceEntity> list = invoiceservice.getAllInvoice();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        response<List<invoiceEntity>> response = new response<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), list);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getInvoiceDetails/{id}")
    public ResponseEntity<response<invoiceEntity>> getInvoiceDetails(@PathVariable int id) {
        Optional<invoiceEntity> findById = invoiceservice.getInvoiceById(id);
        if (findById.isEmpty()) {
            response<invoiceEntity> response = new response<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

        response<invoiceEntity> response = new response<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), findById.get());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/createInvoice")
    public ResponseEntity<response<invoiceEntity>> createInvoice(@RequestBody invoiceEntity invoiceEntity) {
        if(invoiceEntity==null){
            response<invoiceEntity> response = new response<>(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        Optional<purchaseOrder> findPurchaseOrder = purchaseorderservice.findById(invoiceEntity.getPurchaseId());
        if (findPurchaseOrder.isEmpty()) {
            response<invoiceEntity> response = new response<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        invoiceEntity.setPurchaseId(findPurchaseOrder.get().getId());
        Optional<sellersEntity> findSeller = sellerservice.findSellerById(findPurchaseOrder.get().getSellerId());
        if (findSeller.isEmpty()) {
            response<invoiceEntity> response = new response<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

        invoiceEntity.setSellerId(findPurchaseOrder.get().getSellerId());
        invoiceEntity.setTotalPrice(findPurchaseOrder.get().getTotalPrice());
        if(invoiceEntity.getInvoiceDate()==null){
            invoiceEntity.setInvoiceDate(new Date());
        }

        List<orderDetailsEntity> findAllOrderBy = orderdetailsservice.findByPurchaseOrderId(findPurchaseOrder.get().getId());
        int totalQuantity = findAllOrderBy.stream().mapToInt(orderDetailsEntity::getQuantity).sum();
        invoiceEntity.setTotalQuantity(totalQuantity);
        invoiceEntity createInvoice = invoiceservice.saveInvoice(invoiceEntity);
        response<invoiceEntity> response = new response<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), createInvoice);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/deleteInvoice/{id}")
    public ResponseEntity<response<invoiceEntity>> deleteInvoice(@PathVariable int id) {
        Optional<invoiceEntity> findById = invoiceservice.getInvoiceById(id);
        if (findById.isEmpty()) {
            response<invoiceEntity> response = new response<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        boolean deleted = invoiceservice.deleteInvoice(id);
        if (deleted) {
            response<invoiceEntity> response = new response<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        response<invoiceEntity> response = new response<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/getDetailsInInvoice")
    public ResponseEntity<response<Map<String, Object>>> getDetailsInInvoice(@RequestParam int id) {
        Optional<invoiceEntity> findById = invoiceservice.getInvoiceById(id);
        if (findById.isEmpty()) {
            response<Map<String, Object>> response = new response<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        invoiceEntity invoice = findById.get();
        Optional<sellersEntity> findSeller = sellerservice.findSellerById(findById.get().getSellerId());
        sellersEntity seller = findSeller.get();
        Optional<purchaseOrder> findPurchaseOrder = purchaseorderservice.findById(invoice.getPurchaseId());
        purchaseOrder purchaseOrder = findPurchaseOrder.get();
        List<orderDetailsEntity> findAllOrderBy = orderdetailsservice.findByPurchaseOrderId(findPurchaseOrder.get().getId());
        Map<String,Object> sellerData = new HashMap<>();
        sellerData.put("sellerName",seller.getSellerName());
        sellerData.put("sellerAddress",seller.getSellerAddress());
        sellerData.put("sellerAddress",seller.getSellerAddress());
        sellerData.put("sellerContectDetails",seller.getSellerMobileNumber());
        Map<String,Object> purchaseData = new HashMap<>();
        purchaseData.put("contactInfo",purchaseOrder.getContactInfo());
        purchaseData.put("shippingAddress",purchaseOrder.getShippingAddress());
        purchaseData.put("purchaseDate",purchaseOrder.getOrderDate());
        purchaseData.put("orderDetails", findAllOrderBy);
        purchaseData.put("sellerContectDetails",seller.getSellerMobileNumber());
        Map<String, Object> invoiceData = new HashMap<>();
        invoiceData.put("sellerId", sellerData);
        invoiceData.put("purchaseId", purchaseData);
        invoiceData.put("totalPrice", invoice.getTotalPrice());
        invoiceData.put("totalQuantity", invoice.getTotalQuantity());
        invoiceData.put("invoiceDate", invoice.getInvoiceDate());

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("invoice", invoiceData);

        response<Map<String, Object>> response = new response<>(HttpStatus.OK.value(), "Invoice and Seller details", responseData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
