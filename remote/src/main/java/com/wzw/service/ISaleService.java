package com.wzw.service;

import com.wzw.utils.Page;
import com.wzw.vo.SaleVo;

import java.util.ArrayList;

/**
 * @Description TODO
 * @Date 2019/4/6 1:18
 * @Created by wzw
 */
public interface ISaleService {
    Boolean saveSale(SaleVo saleVo);

    Boolean deleteSale(int id);

    Boolean updateSale(SaleVo saleVo);

    SaleVo getSaleById(int id);


    Integer countAllSale();

    Page<SaleVo> allSale(int page, int limit);
}
