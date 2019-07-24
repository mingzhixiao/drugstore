package com.wzw.mapper;

import com.wzw.entity.MedicineInformation;

/**
 * @Classname InAndOutStore
 * @Description TODO
 * @Date 2019/5/17 11:01
 * @Created by wzw
 */
public interface InAndOutStore {

    MedicineInformation findMedicine(int medicineId);


}
