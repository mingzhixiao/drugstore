package com.wzw.service;

import com.wzw.utils.Page;
import com.wzw.vo.MedicineVo;

import java.util.ArrayList;


/**
 * @program: drugstore
 * @description: 药品操作
 * @author: wzw
 * @create: 2019-03-25 14:12
 */
public interface IMedicineService {

    Boolean saveMedicine(MedicineVo medicineVo);

    Boolean deleteMedicineById(int id);

    Boolean updateMedicine(MedicineVo medicineVo);

    MedicineVo getMedicineById(int id);

    Integer countAllMedicine();

    Page<MedicineVo> allMedicine(int page, int limit);
}
