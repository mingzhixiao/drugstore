package com.wzw.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @Description TODO
 * @Date 2019/5/17 14:58
 * @Created by wzw
 */
public class MedicineInformation{




    private String name;


    private Integer id;

    private Integer medicineMenuNumber;

    private String type;

    private String simplify;

    private String shape;

    private Integer format;

    private Integer validate;

    @JSONField(format = "yyyy-MM-dd")
    private Date productionDate;


    private Integer medicineMin;

    private Integer medicineMax;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public MedicineInformation() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicineMenuNumber() {
        return medicineMenuNumber;
    }

    public void setMedicineMenuNumber(Integer medicineMenuNumber) {
        this.medicineMenuNumber = medicineMenuNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSimplify() {
        return simplify;
    }

    public void setSimplify(String simplify) {
        this.simplify = simplify;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public Integer getValidate() {
        return validate;
    }

    public void setValidate(Integer validate) {
        this.validate = validate;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    @Override
    public String toString() {
        return "MedicineInformation{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", medicineMenuNumber=" + medicineMenuNumber +
                ", type='" + type + '\'' +
                ", simplify='" + simplify + '\'' +
                ", shape='" + shape + '\'' +
                ", format=" + format +
                ", validate=" + validate +
                ", productionDate=" + productionDate +
                ", medicineMin=" + medicineMin +
                ", medicineMax=" + medicineMax +
                '}';
    }
}
