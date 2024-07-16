package com.farmerManagement.farmer.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "mt_vegetables")
public class vegetableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vegetableId")
    private int id;
    @Column(name = "vegetableName")
    private String vegetableName;
    @Column(name = "vegetablePrice")
    private double vegetablePrice;
    @Column(name = "vegetableQuantity")
    private int vegetableQuantity;
    @Column(name = "perUntPrice")
    private String perUnitPrice;

    public vegetableEntity(int id, String vegetableName, double vegetablePrice, int vegetableQuantity, String perUnitPrice) {
        this.id = id;
        this.vegetableName = vegetableName;
        this.vegetablePrice = vegetablePrice;
        this.vegetableQuantity = vegetableQuantity;
        this.perUnitPrice = perUnitPrice;
    }

    public vegetableEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVegetableName() {
        return vegetableName;
    }

    public void setVegetableName(String vegetableName) {
        this.vegetableName = vegetableName;
    }

    public Double getVegetablePrice() {
        return vegetablePrice;
    }

    public void setVegetablePrice(Double vegetablePrice) {
        this.vegetablePrice = vegetablePrice;
    }

    public Integer getVegetableQuantity() {
        return vegetableQuantity;
    }

    public void setVegetableQuantity(Integer vegetableQuantity) {
        this.vegetableQuantity = vegetableQuantity;
    }

    public String getPerUnitPrice() {
        return perUnitPrice;
    }

    public void setPerUnitPrice(String perUnitPrice) {
        this.perUnitPrice = perUnitPrice;
    }

    public void setVegetablePrice(double vegetablePrice) {
        this.vegetablePrice = vegetablePrice;
    }

}
