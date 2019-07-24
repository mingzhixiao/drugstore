package com.wzw.service;

import com.wzw.utils.Page;
import com.wzw.vo.SupplierVo;


/**
 * @Description TODO
 * @Date 2019/4/6 1:22
 * @Created by wzw
 */
public interface ISupplierService {
    Boolean saveSupplier(SupplierVo supplierVo);

    Boolean deleteSupplier(int id);

    Boolean updateSupplier(SupplierVo supplierVo);

    SupplierVo getSupplierById(int id);

    Integer countAllSupplier();

    Page<SupplierVo> allSupplier(int page, int limit);
}
