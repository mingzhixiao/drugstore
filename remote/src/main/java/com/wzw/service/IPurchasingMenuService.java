package com.wzw.service;

import com.wzw.utils.Page;
import com.wzw.vo.PurchasingMenuVo;

import java.util.ArrayList;

/**
 * @Classname IPurchasingMenuService
 * @Description TODO
 * @Date 2019/4/6 1:07
 * @Created by wzw
 */
public interface IPurchasingMenuService {
    Boolean savePurchasingMenu(PurchasingMenuVo purchasingMenuVo);

    Boolean deletePurchasingMenu(int id);

    Boolean updatePurchasingMenu(PurchasingMenuVo purchasingMenuVo);

    PurchasingMenuVo getPurchasingMenuById(int id);

    Integer countAllPurchasingMenu();

    Page<PurchasingMenuVo> allPurchasingMenu(int page, int limit);


}
