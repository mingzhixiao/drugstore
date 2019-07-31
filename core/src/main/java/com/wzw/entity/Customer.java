package com.wzw.entity;

public class Customer {
    private Integer id;
    /**
     * 姓名（test）
     */
    private String name;
    /**
     * 手机号(test)
     */

    private String phone;
    /**
     * 权限(test)
     */
    private Integer set;
    /**
     * 邮箱(test)
     */
    private String email;
    /**
     * 状态(test)
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getSet() {
        return set;
    }

    public void setSet(Integer set) {
        this.set = set;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}