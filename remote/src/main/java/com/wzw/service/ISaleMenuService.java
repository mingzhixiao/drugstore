package com.wzw.service;

import com.wzw.utils.Page;
import com.wzw.vo.SaleMenuVo;

import java.util.ArrayList;

/**
 * @Classname ISaleMenuService
 * @Description TODO
 * @Date 2019/4/6 1:15
 * @Created by wzw
 */
public interface ISaleMenuService {
    Boolean saveSaleMenu(SaleMenuVo saleMenuVo);

    Boolean deleteSaleMenu(int id);

    Boolean updateSaleMenu(SaleMenuVo saleMenuVo);

    SaleMenuVo getSaleMenuById(int id);


    Integer countAllSaleMenu();

    Page<SaleMenuVo> allSaleMenu(int page, int limit);
}
