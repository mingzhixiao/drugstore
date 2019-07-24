package com.wzw.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wzw.entity.MedicineMenu;
import com.wzw.mapper.MedicineMapper;
import com.wzw.mapper.MedicineMenuMapper;
import com.wzw.utils.MyArrayListPageConvert;
import com.wzw.utils.Page;
import com.wzw.vo.MedicineMenuVo;
import com.wzw.vo.MedicineVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

import static com.wzw.utils.MyTrueAndFalse.myTrueAndFalse;

/**
 * @Description TODO
 * @Date 2019/4/6 10:56
 * @Created by wzw
 */

@Service("medicineMenuService")
public class IMedicineMenuServiceImpl implements IMedicineMenuService {
    @Resource
    MedicineMenuMapper medicineMenuMapper;
    @Override
    public Boolean saveMedicineMenu(MedicineMenuVo medicineMenuVo) {
        MedicineMenu medicineMenu = JSONObject.parseObject(JSONObject.toJSONString(medicineMenuVo), MedicineMenu.class);
        int i = 0;
        try {
            i = medicineMenuMapper.insertSelective(medicineMenu);
        } catch (Exception e){
            System.out.println("药品单 有误");
        }
        return myTrueAndFalse(i);
    }

    @Override
    public Boolean deleteMedicineMenu(int id) {
        int i = medicineMenuMapper.deleteByPrimaryKey(id);
        return myTrueAndFalse(i);
    }

    @Override
    public Boolean update(MedicineMenuVo medicineMenuVo) {
        MedicineMenu medicineMenu = JSONObject.parseObject(JSONObject.toJSONString(medicineMenuVo), MedicineMenu.class);
        int i = 0;
        try {
            i = medicineMenuMapper.updateByPrimaryKeySelective(medicineMenu);
        } catch (Exception e) {
            System.out.println("药品有误");
        }
        return myTrueAndFalse(i);
    }

    @Override
    public MedicineMenuVo getMedicineByNum(int number) {
        MedicineMenu medicineMenu = medicineMenuMapper.selectByPrimaryKey(number);
        MedicineMenuVo medicineMenuVo = JSONObject.parseObject(JSONObject.toJSONString(medicineMenu), MedicineMenuVo.class);
        return medicineMenuVo;
    }

    @Override
    public Integer countAllMedicineMenu() {
        return medicineMenuMapper.countAllMedicineMenu();
    }

    @Override
    public Page<MedicineMenuVo> allMedicineMenu(int page, int limit) {
        int count = medicineMenuMapper.countAllMedicineMenu();
        PageHelper.startPage(page,limit);
        ArrayList<MedicineMenu> medicineMenus = medicineMenuMapper.allMedicineMenu();
        Page<MedicineMenuVo> medicineMenuVoPage = new MyArrayListPageConvert().myPageConvert(page, limit, count, medicineMenus);
        return medicineMenuVoPage;
    }
}
