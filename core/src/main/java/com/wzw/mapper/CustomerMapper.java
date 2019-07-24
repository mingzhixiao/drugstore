package com.wzw.mapper;

import com.wzw.entity.Customer;

import java.util.ArrayList;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    ArrayList<Customer> allCustomer();

    int countAllCustomer();

}