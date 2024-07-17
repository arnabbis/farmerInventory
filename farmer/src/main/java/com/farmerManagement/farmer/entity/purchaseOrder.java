package com.farmerManagement.farmer.entity;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "mt_purchaseOrder")
public class purchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchaseOrderId")
    private int id;
    @Column(name = "sellerId")
    private Integer sellerId;
    @Column(name = "contactInfo")
    private String contactInfo;
    @Column(name = "shippingAddress")
    private String shippingAddress;
    @Column(name = "totalPrice")
    private double totalPrice;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "orderDate")
    private Date orderDate;

    public purchaseOrder() {
    }

    public purchaseOrder(int id, Integer sellerId, String contactInfo, String shippingAddress, double totalPrice, Date orderDate) {
        this.id = id;
        this.sellerId = sellerId;
        this.contactInfo = contactInfo;
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
