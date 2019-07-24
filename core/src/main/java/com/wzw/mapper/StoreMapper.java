package com.wzw.mapper;


import com.wzw.entity.Store;

import java.util.ArrayList;
import java.util.List;

public interface StoreMapper {
    int deleteByPrimaryKey(Integer medicineId);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer medicineId);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);

    ArrayList<Store> allStore();

    int countAllStore();




    //搜索库存小于一定数量的药品
    ArrayList<Store> listByAmount();



}