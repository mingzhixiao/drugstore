package com.wzw.mapper;

import com.wzw.entity.MedicineMenu;

import java.util.ArrayList;

public interface MedicineMenuMapper {
    int deleteByPrimaryKey(Integer number);

    int insert(MedicineMenu record);

    int insertSelective(MedicineMenu record);

    MedicineMenu selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(MedicineMenu record);

    int updateByPrimaryKey(MedicineMenu record);

    ArrayList<MedicineMenu> allMedicineMenu();

    int countAllMedicineMenu();
}