package com.wzw.service;

import com.wzw.utils.Page;
import com.wzw.vo.IncomeVo;

import java.util.ArrayList;

/**
 * @Description TODO
 * @Date 2019/4/6 0:58
 * @Created by wzw
 */
public interface IIncomeService {
    Boolean saveIncome(IncomeVo incomeVo);

    Boolean deleteIncome(int id);

    Boolean updateIncome(IncomeVo incomeVo);

    IncomeVo getIncomeById(int id);

    Integer countAllIncome();

    Page<IncomeVo> allIncome(int page, int limit);


}
