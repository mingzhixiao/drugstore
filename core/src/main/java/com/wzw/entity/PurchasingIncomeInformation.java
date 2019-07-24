package com.wzw.entity;

import java.util.Date;

/**
 * @Description TODO
 * @Date 2019/5/17 22:59
 * @Created by wzw
 */
public class PurchasingIncomeInformation {
    private int medicineId;
    private int number;
    private String name;
    private Date date;
    private Float totalIncome;


    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Float totalIncome) {
        this.totalIncome = totalIncome;
    }
}
