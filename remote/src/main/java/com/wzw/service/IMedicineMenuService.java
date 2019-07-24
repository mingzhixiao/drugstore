package com.wzw.service;

import com.wzw.utils.Page;
import com.wzw.vo.MedicineMenuVo;



/**
 * @Description TODO
 * @Date 2019/4/6 0:50
 * @Created by wzw
 */
public interface IMedicineMenuService {
    Boolean saveMedicineMenu(MedicineMenuVo medicineMenuVo);

    Boolean deleteMedicineMenu(int id);

    Boolean update(MedicineMenuVo medicineMenuVo);

    MedicineMenuVo getMedicineByNum(int number);


    Integer countAllMedicineMenu();

    Page<MedicineMenuVo> allMedicineMenu(int page, int limit);




}
