package com.farmerManagement.farmer.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Date;
@Entity
@Table(name = "mt_inventory")
public class inventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int inventoryId;
    @Column(name = "inventoryName")
    private String inventoryName;
    @Column(name = "inventoryLocation")
    private String inventoryLocation;
    @Column(name = "vegetableId")
    private Integer vegetableId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    public inventoryEntity() {
    }

    public inventoryEntity(int inventoryId, String inventoryName, String inventoryLocation, Integer vegetableId, Date date) {
        this.inventoryId = inventoryId;
        this.inventoryName = inventoryName;
        this.inventoryLocation = inventoryLocation;
        this.vegetableId = vegetableId;
        this.date = date;
    }


    @Override
    public String toString() {
        return "inventoryEntity{" +
                "inventoryId=" + inventoryId +
                ", inventoryName='" + inventoryName + '\'' +
                ", inventoryLocation='" + inventoryLocation + '\'' +
                ", vegetableId=" + vegetableId +
                ", date=" + date +
                '}';
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public String getInventoryLocation() {
        return inventoryLocation;
    }

    public void setInventoryLocation(String inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }

    public Integer getVegetableId() {
        return vegetableId;
    }



    public void setVegetableId(Integer vegetableId) {
        this.vegetableId = vegetableId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
