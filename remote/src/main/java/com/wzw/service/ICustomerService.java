package com.wzw.service;

import com.wzw.utils.Page;
import com.wzw.vo.CustomerVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Date 2019/3/27 19:59
 * @Created by wzw
 */
public interface ICustomerService {
    Boolean insertCustomerVo(CustomerVo customerVo);

    Boolean deleteCustomerVo(int num);

    Boolean updateCustomerVo(CustomerVo customerVo);


    CustomerVo getCustomerBynNum(int num);

    Integer countAllCustomer();

    Page<CustomerVo> allCustomerVo(int page, int limit);



}
