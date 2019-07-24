package com.wzw.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wzw.entity.Income;
import com.wzw.entity.Purchasing;
import com.wzw.entity.PurchasingIncomeInformation;
import com.wzw.mapper.IncomeMapper;
import com.wzw.mapper.PurchasingIncomeMapper;
import com.wzw.mapper.PurchasingMapper;
import com.wzw.mapper.StoreMapper;
import com.wzw.utils.MyArrayListPageConvert;
import com.wzw.utils.Page;
import com.wzw.vo.PurchasingVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.wzw.utils.MyTrueAndFalse.myTrueAndFalse;

/**
 * @Description TODO
 * @Date 2019/4/6 10:59
 * @Created by wzw
 */

@Service("purchasingService")
public class IPurchasingServiceImpl implements IPurchasingService {
    @Resource
    PurchasingMapper purchasingMapper;


    @Resource
    IStoreService  storeService;

    @Resource
    PurchasingIncomeMapper purchasingIncomeMapper;


    @Resource
    IncomeMapper incomeMapper;


    @Override
    public Boolean savePurchasing(PurchasingVo purchasingVo) {
        Purchasing purchasing = JSONObject.parseObject(JSONObject.toJSONString(purchasingVo), Purchasing.class);
        int insert = 0;
        try {
            insert = purchasingMapper.insert(purchasing);
        } catch (Exception e) {
            System.out.println("添加有误");
        }

        //进货时修改库存药品的数量
        storeService.purchasingInStore(purchasing.getStoreMedicineId(),purchasing.getAmount());

        //进货时添加收入
        Integer storeMedicineId = purchasingVo.getStoreMedicineId();
        PurchasingIncomeInformation purchasingIncomeInformation = purchasingIncomeMapper.inIncome(storeMedicineId);

        System.out.println("www"+purchasingIncomeInformation);

        Date date = purchasingIncomeInformation.getDate();

        String name = purchasingIncomeInformation.getName();
        Float totalIncome = purchasingIncomeInformation.getTotalIncome();
        int number = purchasingIncomeInformation.getNumber();
        SimpleDateFormat years = new SimpleDateFormat("yyyy");
        String year = years.format(date);
        SimpleDateFormat months = new SimpleDateFormat("MM");
        String month = months.format(date).substring(1);
        float expends = 0;
        Income income = new Income(storeMedicineId,number,name,expends,totalIncome,month,year,-totalIncome);
        incomeMapper.insertSelective(income);

        return myTrueAndFalse(insert);
    }

    @Override
    public Boolean deletePurchasing(int id) {
        int i = purchasingMapper.deleteByPrimaryKey(id);
        return myTrueAndFalse(i);
    }

    @Override
    public Boolean updatePurchasing(PurchasingVo purchasingVo) {
        Purchasing purchasing = JSONObject.parseObject(JSONObject.toJSONString(purchasingVo), Purchasing.class);
        int i = 0;
        try{
            i = purchasingMapper.updateByPrimaryKeySelective(purchasing);
        } catch (Exception e){

        }
        return myTrueAndFalse(i);
    }

    @Override
    public PurchasingVo getMedicineById(int id) {
        Purchasing purchasing = purchasingMapper.selectByPrimaryKey(id);
        PurchasingVo purchasingVo = JSONObject.parseObject(JSONObject.toJSONString(purchasing), PurchasingVo.class);
        return purchasingVo;
    }

    @Override
    public Integer countAllPurchasing() {
        return purchasingMapper.countAllPurchasing();
    }

    @Override
    public Page<PurchasingVo> allPurchasing(int page, int limit) {
        int count = purchasingMapper.countAllPurchasing();
        PageHelper.startPage(page,limit);
        ArrayList<Purchasing> purchasings = purchasingMapper.allPurchasing();
        Page<PurchasingVo> purchasingVoPage = new MyArrayListPageConvert().myPageConvert(page, limit, count, purchasings);
        return purchasingVoPage;
    }
}
