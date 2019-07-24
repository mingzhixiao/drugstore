package com.wzw.vo;

import java.io.Serializable;

public class MedicineMenuVo implements Serializable {
    private Integer number;

    private String name;

    private Integer status;

    private Integer medicineMin;

    private Integer medicineMax;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMedicineMin() {
        return medicineMin;
    }

    public void setMedicineMin(Integer medicineMin) {
        this.medicineMin = medicineMin;
    }

    public Integer getMedicineMax() {
        return medicineMax;
    }

    public void setMedicineMax(Integer medicineMax) {
        this.medicineMax = medicineMax;
    }
}