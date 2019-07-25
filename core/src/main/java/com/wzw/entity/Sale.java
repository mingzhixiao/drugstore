package com.wzw.entity;

public class Sale {
    /**
     * 主 id
     */
    private Integer id;
    /**
     * 主 销售详情id
     */
    private Integer saleMenuId;
    /**
     * 主 药品id
     */
    private Integer storeMedicineId;
    /**
     * 主 药品价格
     */
    private Float salePrice;
    /**
     * 主 药品vip价格
     */
    private Float saleVipPrice;
    /**
     * 主 药品数量
     */
    private Integer amount;
    /**
     * 主 总价
     */
    private Float totalPrice;
    /**
     * 主  药品状态
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSaleMenuId() {
        return saleMenuId;
    }

    public void setSaleMenuId(Integer saleMenuId) {
        this.saleMenuId = saleMenuId;
    }

    public Integer getStoreMedicineId() {
        return storeMedicineId;
    }

    public void setStoreMedicineId(Integer storeMedicineId) {
        this.storeMedicineId = storeMedicineId;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public Float getSaleVipPrice() {
        return saleVipPrice;
    }

    public void setSaleVipPrice(Float saleVipPrice) {
        this.saleVipPrice = saleVipPrice;
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