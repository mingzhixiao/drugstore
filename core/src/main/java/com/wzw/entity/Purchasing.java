package com.wzw.entity;

public class Purchasing {
    private Integer id;

    private Integer purchasingMenuId;

    private Integer storeMedicineId;

    private Float purchasingPrice;

    private Integer amount;

    private Float totalPrice;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPurchasingMenuId() {
        return purchasingMenuId;
    }

    public void setPurchasingMenuId(Integer purchasingMenuId) {
        this.purchasingMenuId = purchasingMenuId;
    }

    public Integer getStoreMedicineId() {
        return storeMedicineId;
    }

    public void setStoreMedicineId(Integer storeMedicineId) {
        this.storeMedicineId = storeMedicineId;
    }

    public Float getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(Float purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}