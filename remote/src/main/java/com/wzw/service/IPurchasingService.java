package com.wzw.service;


import com.wzw.utils.Page;
import com.wzw.vo.PurchasingVo;

import java.util.ArrayList;

/**
 * @Description TODO
 * @Date 2019/4/6 1:11
 * @Created by wzw
 */
public interface IPurchasingService {
    Boolean savePurchasing(PurchasingVo purchasingVo);

    Boolean deletePurchasing(int id);

    Boolean updatePurchasing(PurchasingVo purchasingVo);

    PurchasingVo getMedicineById(int id);

    Integer countAllPurchasing();

    Page<PurchasingVo> allPurchasing(int page, int limit);
}
