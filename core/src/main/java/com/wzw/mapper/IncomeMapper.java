package com.wzw.mapper;

import com.wzw.entity.Income;

import java.util.ArrayList;
import java.util.Date;

public interface IncomeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Income record);

    int insertSelective(Income record);

    Income selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Income record);

    int updateByPrimaryKey(Income record);

    int countAllIncome();

    ArrayList<Income> allIncome();


    //按照时间来搜索
    ArrayList<Income> listByDate(Date dateFrom, Date dateEnd);


    //按照名称来搜索
    ArrayList<Income> listByName(String name);

    //按照药品编号来搜索
    ArrayList<Income> listByNumber(int number);


    //搜索最高盈利的药品
    Income getByMostIncome();

    //搜索最近一个月的最高五个盈利药品
    ArrayList<Income> listByFiveMostIncome();


}