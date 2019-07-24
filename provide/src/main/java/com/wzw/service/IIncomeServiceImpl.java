package com.wzw.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wzw.entity.Income;
import com.wzw.mapper.IncomeMapper;
import com.wzw.utils.MyArrayListPageConvert;
import com.wzw.utils.Page;
import com.wzw.vo.IncomeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import static com.wzw.utils.MyTrueAndFalse.myTrueAndFalse;

/**
 * @Description TODO
 * @Date 2019/4/6 11:11
 * @Created by wzw
 */
@Service("incomeService")
public class IIncomeServiceImpl implements IIncomeService {
    @Resource
    IncomeMapper incomeMapper;
    @Override
    public Boolean saveIncome(IncomeVo incomeVo) {
        Income income = JSONObject.parseObject(JSONObject.toJSONString(incomeVo), Income.class);
        int i = 0;
        try{
            i = incomeMapper.insertSelective(income);
        } catch (Exception e) {
            System.out.println("填写数据有误");
        }
        return myTrueAndFalse(i);
    }

    @Override
    public Boolean deleteIncome(int id) {
        int i = incomeMapper.deleteByPrimaryKey(id);
        return myTrueAndFalse(i);
    }

    @Override
    public Boolean updateIncome(IncomeVo incomeVo) {
        Income income = JSONObject.parseObject(JSONObject.toJSONString(incomeVo), Income.class);
        int i = 0;
        try{
             i = incomeMapper.updateByPrimaryKeySelective(income);
        } catch (Exception e){
            System.out.println("输入有误");
        }
        return myTrueAndFalse(i);
    }

    @Override
    public IncomeVo getIncomeById(int id) {
        Income income = incomeMapper.selectByPrimaryKey(id);
        IncomeVo incomeVo = JSONObject.parseObject(JSONObject.toJSONString(income), IncomeVo.class);
        return incomeVo;
    }

    @Override
    public Integer countAllIncome() {
        int count = incomeMapper.countAllIncome();
        return count;
    }

    @Override
    public Page<IncomeVo> allIncome(int page, int limit) {
        int count = incomeMapper.countAllIncome();
        PageHelper.startPage(page,limit);
        ArrayList<Income> incomes = incomeMapper.allIncome();
        Page<IncomeVo> incomeVoPage = new MyArrayListPageConvert().myPageConvert(page, limit, count, incomes);
        return incomeVoPage;
    }
}
