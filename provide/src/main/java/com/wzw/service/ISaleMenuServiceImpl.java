package com.wzw.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wzw.entity.SaleMenu;
import com.wzw.mapper.SaleMenuMapper;
import com.wzw.utils.MyArrayListPageConvert;
import com.wzw.utils.Page;
import com.wzw.vo.SaleMenuVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

import static com.wzw.utils.MyTrueAndFalse.myTrueAndFalse;

/**
 * @Description TODO
 * @Date 2019/4/6 11:02
 * @Created by wzw
 */

@Service("saleMenuService")
public class ISaleMenuServiceImpl implements ISaleMenuService {
    @Resource
    SaleMenuMapper saleMenuMapper;
    @Override
    public Boolean saveSaleMenu(SaleMenuVo saleMenuVo) {
        SaleMenu saleMenu = JSONObject.parseObject(JSONObject.toJSONString(saleMenuVo), SaleMenu.class);
        int i = 0;
        try {
            i = saleMenuMapper.insertSelective(saleMenu);
        } catch (Exception e){

        }
        return myTrueAndFalse(i);
    }

    @Override
    public Boolean deleteSaleMenu(int id) {
        int i = saleMenuMapper.deleteByPrimaryKey(id);
        return myTrueAndFalse(i);
    }

    @Override
    public Boolean updateSaleMenu(SaleMenuVo saleMenuVo) {
        SaleMenu saleMenu = JSONObject.parseObject(JSONObject.toJSONString(saleMenuVo), SaleMenu.class);
        int i = 0;
        try{
            i = saleMenuMapper.updateByPrimaryKeySelective(saleMenu);
        } catch (Exception e) {

        }
        return myTrueAndFalse(i);
    }

    @Override
    public SaleMenuVo getSaleMenuById(int id) {
        SaleMenu saleMenu = saleMenuMapper.selectByPrimaryKey(id);
        SaleMenuVo saleMenuVo = JSONObject.parseObject(JSONObject.toJSONString(saleMenu), SaleMenuVo.class);
        return saleMenuVo;
    }

    @Override
    public Integer countAllSaleMenu() {
        return saleMenuMapper.countAllSaleMenu();
    }

    @Override
    public Page<SaleMenuVo> allSaleMenu(int page, int limit) {
        int count = saleMenuMapper.countAllSaleMenu();
        PageHelper.startPage(page,limit);
        ArrayList<SaleMenu> saleMenus = saleMenuMapper.allSaleMenu();
        Page<SaleMenuVo> saleMenuVoPage = new MyArrayListPageConvert().myPageConvert(page, limit, count, saleMenus);
        return saleMenuVoPage;
    }
}
