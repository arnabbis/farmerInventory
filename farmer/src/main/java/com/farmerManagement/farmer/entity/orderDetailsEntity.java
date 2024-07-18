package com.farmerManagement.farmer.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "mt_orderDetails")
public class orderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderDetailsId")
    private int orderDetailsId;
    @Column(name = "purchaseOrderId")
    private Integer purchaseOrderId;
    @Column(name = "vegetableId")
    private Integer vegetableId;
    @Column (name = "TotalPrice")
    private double totalPrice;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "orderDate")
    private Date orderDate;
    @Column(name = "quantity")
    private Integer quantity;

    public orderDetailsEntity() {
    }

    public orderDetailsEntity(int orderDetailsId, Integer purchaseOrderId, Integer vegetableId, double totalPrice, Date orderDate, Integer quantity) {
        this.orderDetailsId = orderDetailsId;
        this.purchaseOrderId = purchaseOrderId;
        this.vegetableId = vegetableId;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.quantity = quantity;
    }

    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderDetailsId(int orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Integer getVegetableId() {
        return vegetableId;
    }

    public void setVegetableId(Integer vegetableId) {
        this.vegetableId = vegetableId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
