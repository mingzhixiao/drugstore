package com.wzw.mapper;

import com.wzw.entity.SaleMenu;

import java.util.ArrayList;

public interface SaleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaleMenu record);

    int insertSelective(SaleMenu record);

    SaleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaleMenu record);

    int updateByPrimaryKey(SaleMenu record);

    ArrayList<SaleMenu> allSaleMenu();

    int countAllSaleMenu();
}