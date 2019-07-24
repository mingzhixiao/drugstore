package com.wzw.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wzw.entity.Supplier;
import com.wzw.mapper.SupplierMapper;
import com.wzw.utils.MyArrayListPageConvert;
import com.wzw.utils.Page;
import com.wzw.vo.SupplierVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import static com.wzw.utils.MyTrueAndFalse.myTrueAndFalse;


/**
 * @Description TODO
 * @Date 2019/4/6 11:05
 * @Created by wzw
 */

@Service("supplierService")
public class ISupplierServiceImpl implements ISupplierService {
    @Resource
    SupplierMapper supplierMapper;

    @Override
    public Boolean saveSupplier(SupplierVo supplierVo) {
        Supplier supplier = JSONObject.parseObject(JSONObject.toJSONString(supplierVo), Supplier.class);
        int i = 0;
        try {
            i = supplierMapper.insert(supplier);

        } catch (Exception e) {

        }
        return myTrueAndFalse(i);
    }

    @Override
    public Boolean deleteSupplier(int id) {
        int i = supplierMapper.deleteByPrimaryKey(id);
        return myTrueAndFalse(i);

    }

    @Override
    public Boolean updateSupplier(SupplierVo supplierVo) {
        Supplier supplier = JSONObject.parseObject(JSONObject.toJSONString(supplierVo), Supplier.class);
        System.out.println(supplier.getAddress());

       int  i = supplierMapper.updateByPrimaryKey(supplier);

        System.out.println(i);
        return myTrueAndFalse(i);
    }

    @Override
    public SupplierVo getSupplierById(int id) {
        Supplier supplier = supplierMapper.selectByPrimaryKey(id);
        SupplierVo supplierVo = JSONObject.parseObject(JSONObject.toJSONString(supplier), SupplierVo.class);
        return supplierVo;
    }

    @Override
    public Integer countAllSupplier() {
        return supplierMapper.countAllSupplier();
    }

    @Override
    public Page<SupplierVo> allSupplier(int page, int limit) {
        int count = supplierMapper.countAllSupplier();
        PageHelper.startPage(page,limit);
        ArrayList<Supplier> suppliers = supplierMapper.allSupplier();
        Page<SupplierVo> supplierVoPage = new MyArrayListPageConvert().myPageConvert(page, limit, count, suppliers);
        return supplierVoPage;
    }
}
