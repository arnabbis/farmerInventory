package com.farmerManagement.farmer.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mt_seller")
public class sellersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int sellerId;
    @Column(name = "name")
    private String sellerName;
    @Column(name = "address")
    private String sellerAddress;
    @Column(name = "mobile")
    private String sellerMobileNumber;

    public sellersEntity() {
    }

    public sellersEntity(int sellerId, String sellerName, String sellerAddress, String sellerMobileNumber) {
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.sellerAddress = sellerAddress;
        this.sellerMobileNumber = sellerMobileNumber;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerMobileNumber() {
        return sellerMobileNumber;
    }

    public void setSellerMobileNumber(String sellerMobileNumber) {
        this.sellerMobileNumber = sellerMobileNumber;
    }
}



