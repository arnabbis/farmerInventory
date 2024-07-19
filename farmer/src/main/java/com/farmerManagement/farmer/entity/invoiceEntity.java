package com.farmerManagement.farmer.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "mt_invoice")
public class invoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoiceId")
    private int id;
    @Column(name = "sellerId")
    private Integer sellerId;
    @Column(name = "purchaseId")
    private Integer purchaseId;
    @Column(name = "totalPrice")
    private Double totalPrice;
    @Column(name = "totalQuantity")
    private Integer totalQuantity;
    @Column(name = "invoiveDate")
    private Date invoiceDate;

    public invoiceEntity() {
    }

    public invoiceEntity(int id, Integer sellerId, Integer purchaseId, Double totalPrice, Integer totalQuantity, Date invoiceDate) {
        this.id = id;
        this.sellerId = sellerId;
        this.purchaseId = purchaseId;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.invoiceDate = invoiceDate;
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

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
