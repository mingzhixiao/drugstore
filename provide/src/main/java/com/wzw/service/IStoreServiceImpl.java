package com.wzw.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wzw.entity.Medicine;
import com.wzw.entity.MedicineInformation;
import com.wzw.entity.Store;
import com.wzw.mapper.InAndOutStore;
import com.wzw.mapper.MedicineMapper;
import com.wzw.mapper.StoreMapper;
import com.wzw.utils.MyArrayListPageConvert;
import com.wzw.utils.Page;
import com.wzw.vo.StoreVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

import static com.wzw.utils.MyTrueAndFalse.myTrueAndFalse;

/**
 * @program: drugstore
 * @description: 药品库存信息操作
 * @author: wzw
 * @create: 2019-03-28 17:26
 */
@Service("storeService")
public class IStoreServiceImpl implements IStoreService {
    @Resource
    StoreMapper storeMapper;

    @Resource
    InAndOutStore inAndOutStore;

    @Resource
    MedicineMapper medicineMapper;


    @Override
    public Boolean insertStore(StoreVo storeVo) {
        Store store = JSONObject.parseObject(JSONObject.toJSONString(storeVo), Store.class);
        int i = 0;
        try {
            i = storeMapper.insert(store);
        } catch (Exception e){

        }
        return myTrueAndFalse(i);
    }


    //当添加库存药品时添加一个库存为-1的药品
    @Override
    public Boolean inStore(int medicineID){
        Medicine medicine = medicineMapper.selectByPrimaryKey(medicineID);
        Integer medicineMenuNumber = medicine.getMedicineMenuNumber();
        MedicineInformation medicineInformation = inAndOutStore.findMedicine(medicineID);
        Integer deadline = medicineInformation.getValidate();
        Integer medicineMax = medicineInformation.getMedicineMax();
        System.out.println("medicineMax  "+medicineMax);
        Integer medicineMin = medicineInformation.getMedicineMin();
        System.out.println(" medicineMin" + medicineMin);
        Date productionDate = medicineInformation.getProductionDate();
        String name = medicineInformation.getName();
        Store store = new Store(medicineID,medicineMenuNumber,name,medicineMin,medicineMax,-1,deadline,productionDate);
        System.out.println(store);
        int insert = storeMapper.insert(store);
        return myTrueAndFalse(insert);
    }

    //采购时修改库存药品的库存数量
    @Override
    public Boolean purchasingInStore(int medicineId, int amount) {
        Store store = storeMapper.selectByPrimaryKey(medicineId);
        store.setAmount(amount);
        int i = storeMapper.updateByPrimaryKeySelective(store);
        return myTrueAndFalse(i);
    }


    @Override
    public Boolean deleteStore(int id) {
        int i = storeMapper.deleteByPrimaryKey(id);
        return myTrueAndFalse(i);
    }

    @Override
    public Boolean updateStore(StoreVo storeVo) {
        Store store = JSONObject.parseObject(JSONObject.toJSONString(storeVo), Store.class);
        int i = 0;
        try{
            i = storeMapper.updateByPrimaryKeySelective(store);
        } catch (Exception e){

        }
        return myTrueAndFalse(i);
    }

    @Override
    public StoreVo getStoreByNum(int number) {
        Store store = storeMapper.selectByPrimaryKey(number);
        StoreVo storeVo = JSONObject.parseObject(JSONObject.toJSONString(store), StoreVo.class);
        return storeVo;
    }

    @Override
    public Integer countAllStore() {
        return storeMapper.countAllStore();
    }

    @Override
    public Page<StoreVo> allStore(int page, int limit) {
        int count = storeMapper.countAllStore();
        PageHelper.startPage(page,limit);
        ArrayList<Store> stores = storeMapper.allStore();
        Page<StoreVo> storeVoPage = new MyArrayListPageConvert().myPageConvert(page, limit, count, stores);
        return storeVoPage;
    }
}