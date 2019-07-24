package com.wzw.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.wzw.entity.PurchasingMenu;
import com.wzw.mapper.PurchasingMenuMapper;
import com.wzw.utils.MyArrayListPageConvert;
import com.wzw.utils.Page;
import com.wzw.vo.PurchasingMenuVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

import static com.wzw.utils.MyTrueAndFalse.myTrueAndFalse;

/**
 * @Description TODO
 * @Date 2019/4/6 10:58
 * @Created by wzw
 */

@Service("purchasingMenuService")
public class IPurchasingMenuServiceImpl implements IPurchasingMenuService {
    @Resource
    PurchasingMenuMapper purchasingMenuMapper;
    @Override
    public Boolean savePurchasingMenu(PurchasingMenuVo purchasingMenuVo) {
        PurchasingMenu purchasingMenu = JSONObject.parseObject(JSONObject.toJSONString(purchasingMenuVo), PurchasingMenu.class);
        int insert = 0;
        try {
            insert = purchasingMenuMapper.insert(purchasingMenu);
        } catch (Exception e){
            System.out.println("采购单添加有误");
        }
        return myTrueAndFalse(insert);
    }

    @Override
    public Boolean deletePurchasingMenu(int id) {
        int i = purchasingMenuMapper.deleteByPrimaryKey(id);
        return myTrueAndFalse(i);
    }

    @Override
    public Boolean updatePurchasingMenu(PurchasingMenuVo purchasingMenuVo) {
        PurchasingMenu purchasingMenu = JSONObject.parseObject(JSONObject.toJSONString(purchasingMenuVo), PurchasingMenu.class);
        int i = 0;
        try {
            i = purchasingMenuMapper.updateByPrimaryKeySelective(purchasingMenu);
        } catch (Exception e) {
            System.out.println("修改有误");
        }
        return myTrueAndFalse(i);
    }

    @Override
    public PurchasingMenuVo getPurchasingMenuById(int id) {
        PurchasingMenu purchasingMenu = purchasingMenuMapper.selectByPrimaryKey(id);
        PurchasingMenuVo purchasingMenuVo = JSONObject.parseObject(JSONObject.toJSONString(purchasingMenu), PurchasingMenuVo.class);
        return purchasingMenuVo;
    }

    @Override
    public Integer countAllPurchasingMenu() {
        return purchasingMenuMapper.countAllPurchasingMenu();
    }

    @Override
    public Page<PurchasingMenuVo> allPurchasingMenu(int page, int limit) {
        int count = purchasingMenuMapper.countAllPurchasingMenu();
        PageHelper.startPage(page,limit);
        ArrayList<PurchasingMenu> purchasingMenus = purchasingMenuMapper.allPurchasingMenu();
        Page<PurchasingMenuVo> purchasingMenuVoPage = new MyArrayListPageConvert().myPageConvert(page, limit, count, purchasingMenus);
        return  purchasingMenuVoPage;
    }
}
