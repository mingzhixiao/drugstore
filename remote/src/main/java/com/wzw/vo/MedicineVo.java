package com.wzw.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class MedicineVo implements Serializable {
    private Integer id;

    private Integer medicineMenuNumber;

    private String type;

    private String simplify;

    private String shape;

    private Integer format;

    private Integer validate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date productionDate;

    private Integer status;

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
        this.type = type == null ? null : type.trim();
    }

    public String getSimplify() {
        return simplify;
    }

    public void setSimplify(String simplify) {
        this.simplify = simplify == null ? null : simplify.trim();
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape == null ? null : shape.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}