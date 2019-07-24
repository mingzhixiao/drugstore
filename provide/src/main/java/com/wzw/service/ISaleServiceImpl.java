package com.wzw.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wzw.entity.Income;
import com.wzw.entity.Sale;
import com.wzw.entity.Store;
import com.wzw.mapper.SaleMapper;
import com.wzw.mapper.StoreMapper;
import com.wzw.utils.MyArrayListPageConvert;
import com.wzw.utils.Page;
import com.wzw.vo.IncomeVo;
import com.wzw.vo.SaleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

import static com.wzw.utils.MyTrueAndFalse.myTrueAndFalse;

/**
 * @Description TODO
 * @Date 2019/4/6 11:03
 * @Created by wzw
 */
@Service("saleService")
public class ISaleServiceImpl implements ISaleService {
    @Resource
    SaleMapper saleMapper;

    @Resource
    StoreMapper storeMapper;

    @Resource
    IIncomeService incomeService;


    @Override
    public Boolean saveSale(SaleVo saleVo) {
        Sale sale = JSONObject.parseObject(JSONObject.toJSONString(saleVo), Sale.class);
        int i = 0;
        try {
            i = saleMapper.insertSelective(sale);
        } catch (Exception e) {

        }

        //销售减少库存
        Integer medicineId = sale.getStoreMedicineId();
        Store store = storeMapper.selectByPrimaryKey(medicineId);
        Integer saleAmount = sale.getAmount();
        Integer storeAmount = store.getAmount();
        if (saleAmount <= storeAmount){
            store.setAmount(storeAmount - saleAmount);

            //销售成功增加收入

            IncomeVo incomeById = incomeService.getIncomeById(medicineId);
            incomeById.setIncome(sale.getTotalPrice());
            incomeById.setTotalIncome(incomeById.getTotalIncome() + sale.getTotalPrice());
            incomeService.updateIncome(incomeById);
        }
        storeMapper.updateByPrimaryKeySelective(store);





        return myTrueAndFalse(i);
    }

    @Override
    public Boolean deleteSale(int id) {
        int i = saleMapper.deleteByPrimaryKey(id);
        return myTrueAndFalse(i);

    }

    @Override
    public Boolean updateSale(SaleVo saleVo) {
        Sale sale = JSONObject.parseObject(JSONObject.toJSONString(saleVo), Sale.class);
        int i = 0;
        try {
            i = saleMapper.updateByPrimaryKeySelective(sale);
        } catch (Exception e) {

        }
        return myTrueAndFalse(i);
    }

    @Override
    public SaleVo getSaleById(int id) {
        Sale sale = saleMapper.selectByPrimaryKey(id);
        SaleVo saleVo = JSONObject.parseObject(JSONObject.toJSONString(sale), SaleVo.class);
        return saleVo;
    }

    @Override
    public Integer countAllSale() {
        return saleMapper.countAllSale();
    }

    @Override
    public Page<SaleVo> allSale(int page, int limit) {
        int count = saleMapper.countAllSale();
        PageHelper.startPage(page, limit);
        ArrayList<Sale> sales = saleMapper.allSale();
        Page<SaleVo> saleVoPage = new MyArrayListPageConvert().myPageConvert(page, limit, count, sales);
        return saleVoPage;
    }
}
