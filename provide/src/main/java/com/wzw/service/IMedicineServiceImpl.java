package com.wzw.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wzw.entity.Medicine;
import com.wzw.mapper.MedicineMapper;
import com.wzw.utils.MyArrayListPageConvert;
import com.wzw.utils.Page;
import com.wzw.vo.MedicineVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.wzw.utils.MyTrueAndFalse.myTrueAndFalse;

/**
 * @program: drugstore
 * @description: 药品信息的操作
 * @author: wzw
 * @create: 2019-03-25 14:57
 */
@Service("medicineService")
public class IMedicineServiceImpl implements IMedicineService {
    @Resource
    MedicineMapper medicineMapper;
    @Override
    public Boolean saveMedicine(MedicineVo medicineVo) {
        Medicine medicine = JSONObject.parseObject(JSONObject.toJSONString(medicineVo), Medicine.class);
        int i = 0;
        try{
            i = medicineMapper.insertSelective(medicine);
        } catch (Exception e){
            System.out.println("添加有误");
        }
        return myTrueAndFalse(i);
    }

    @Override
    public Boolean deleteMedicineById(int id) {
        int i = medicineMapper.deleteByPrimaryKey(id);
        return myTrueAndFalse(i);
    }

    @Override
    public Boolean updateMedicine(MedicineVo medicineVo) {
        Medicine medicine = JSONObject.parseObject(JSONObject.toJSONString(medicineVo), Medicine.class);
        int i = 0;
        try{
            i = medicineMapper.updateByPrimaryKeySelective(medicine);
        } catch (Exception e){
            System.out.println("修改有误");
        }
        return myTrueAndFalse(i);
    }

    @Override
    public MedicineVo getMedicineById(int id) {
        Medicine medicine = medicineMapper.selectByPrimaryKey(id);
        MedicineVo medicineVo = JSONObject.parseObject(JSONObject.toJSONString(medicine), MedicineVo.class);
        System.out.println("药品编号"+medicineVo.getMedicineMenuNumber());
        return medicineVo;
    }

    @Override
    public Integer countAllMedicine() {
        return medicineMapper.countAllMedicine();
    }

    @Override
    public Page<MedicineVo> allMedicine(int page, int limit) {
        int count = medicineMapper.countAllMedicine();
        PageHelper.startPage(page,limit);
        ArrayList<Medicine> medicines = medicineMapper.allMedicine();
        Page<MedicineVo> medicineVoPage = new MyArrayListPageConvert().myPageConvert(page, limit, count, medicines);
        return medicineVoPage;
    }
}
