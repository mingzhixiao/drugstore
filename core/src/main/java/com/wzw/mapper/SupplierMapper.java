package com.wzw.mapper;

import com.wzw.entity.Supplier;

import java.util.ArrayList;

public interface SupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);

    ArrayList<Supplier> allSupplier();

    int countAllSupplier();
}