package com.wzw.mapper;

import com.wzw.entity.Purchasing;

import java.util.ArrayList;

public interface PurchasingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Purchasing record);

    int insertSelective(Purchasing record);

    Purchasing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Purchasing record);

    int updateByPrimaryKey(Purchasing record);

    ArrayList<Purchasing> allPurchasing();

    int countAllPurchasing();




}