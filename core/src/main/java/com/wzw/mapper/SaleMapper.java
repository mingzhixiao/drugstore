package com.wzw.mapper;

import com.wzw.entity.Sale;

import java.util.ArrayList;

public interface SaleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sale record);

    int insertSelective(Sale record);

    Sale selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sale record);

    int updateByPrimaryKey(Sale record);

    ArrayList<Sale> allSale();

    int countAllSale();
}