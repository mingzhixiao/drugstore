package com.wzw.mapper;

import com.wzw.entity.PurchasingMenu;

import java.util.ArrayList;

public interface PurchasingMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchasingMenu record);

    int insertSelective(PurchasingMenu record);

    PurchasingMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchasingMenu record);

    int updateByPrimaryKey(PurchasingMenu record);

    ArrayList<PurchasingMenu> allPurchasingMenu();

    int countAllPurchasingMenu();
}