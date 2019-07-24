package com.wzw.mapper;

import com.wzw.entity.Medicine;

import java.util.ArrayList;

public interface MedicineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Medicine record);

    int insertSelective(Medicine record);

    Medicine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Medicine record);

    int updateByPrimaryKey(Medicine record);

    ArrayList<Medicine> allMedicine();

    int countAllMedicine();
}