package com.wzw.entity;

public class Income {
    /**
     * 收入id
     */
    private Integer id;
    /**
     * 药品明细编号
     */
    private Integer medicineMenuNumber;
    /**
     * 药品名称
     */
    private String medicineMenuName;
    /**
     * 收入
     */
    private Float income;
    /**
     * 支出
     */
    private Float expense;
    /**
     * 月份
     */
    private String month;
    /**
     * 年份
     */
    private String year;
    /**
     * 总收入
     */
    private Float totalIncome;

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

    public String getMedicineMenuName() {
        return medicineMenuName;
    }

    public void setMedicineMenuName(String medicineMenuName) {
        this.medicineMenuName = medicineMenuName == null ? null : medicineMenuName.trim();
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public Float getExpense() {
        return expense;
    }

    public void setExpense(Float expense) {
        this.expense = expense;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Float getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Float totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Income() {
    }

    public Income(Integer id, Integer medicineMenuNumber, String medicineMenuName, Float income, Float expense, String month, String year, Float totalIncome) {
        this.id = id;
        this.medicineMenuNumber = medicineMenuNumber;
        this.medicineMenuName = medicineMenuName;
        this.income = income;
        this.expense = expense;
        this.month = month;
        this.year = year;
        this.totalIncome = totalIncome;
    }
}