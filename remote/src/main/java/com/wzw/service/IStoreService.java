package com.wzw.service;

import com.wzw.utils.Page;
import com.wzw.vo.StoreVo;

import java.util.ArrayList;
import java.util.Date;


/**
 * @program: drugstore
 * @description: 库存药品信息操作
 * @author: wzw
 * @create: 2019-03-28 16:43
 */
public interface IStoreService {
    Boolean insertStore(StoreVo storeVo);
    Boolean deleteStore(int id);
    Boolean updateStore(StoreVo storeVo);

    StoreVo getStoreByNum(int number);

    Integer countAllStore();
    Page<StoreVo> allStore(int page,int limit);

   Boolean inStore(int medicineId);

   Boolean purchasingInStore(int medicineId,int amount);



}