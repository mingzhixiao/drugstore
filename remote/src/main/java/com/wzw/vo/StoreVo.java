package com.wzw.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class StoreVo implements Serializable {
    private Integer medicineId;

    private Integer medicineMenuNumber;

    private String medicineMenuName;

    private String storeMin;

    private Integer storeMax;

    private Integer amount;

    private Integer deadline;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date productionDate;

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getMedicineMenuNumber() {
        return medicineMenuNumber;
    }

    public void setMedicineMenuNumber(Integer medicineMenuNumber) {
        this.medicineMenuNumber = medicineMenuNumber;
    }

    public String getMedicineMenuName() {
        return medicineMenuName;
    }

    public void setMedicineMenuName(String medicineMenuName) {
        this.medicineMenuName = medicineMenuName == null ? null : medicineMenuName.trim();
    }

    public String getStoreMin() {
        return storeMin;
    }

    public void setStoreMin(String storeMin) {
        this.storeMin = storeMin == null ? null : storeMin.trim();
    }

    public Integer getStoreMax() {
        return storeMax;
    }

    public void setStoreMax(Integer storeMax) {
        this.storeMax = storeMax;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
}