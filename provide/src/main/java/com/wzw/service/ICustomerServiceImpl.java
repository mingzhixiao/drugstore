package com.wzw.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wzw.entity.Customer;
import com.wzw.mapper.CustomerMapper;
import com.wzw.utils.MyArrayListPageConvert;
import com.wzw.utils.Page;
import com.wzw.vo.CustomerVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: drugstore
 * @description: 顾客信息操作
 * @author: wzw
 * @create: 2019-03-27 12:35
 */
@Service("customerService")
public class ICustomerServiceImpl implements  ICustomerService{
    @Resource
    CustomerMapper customerMapper;

    @Override
    public Boolean insertCustomerVo(CustomerVo customerVo) {
        Customer customer = JSONObject.parseObject(JSONObject.toJSONString(customerVo), Customer.class);
        int i = 0;
        try {
            i = customerMapper.insertSelective(customer);
        } catch (Exception e) {
            System.out.println("填写数据有误");
        }
            if (i > 0) {
                return true;
        }
        return false;
    }

    @Override
    public Boolean deleteCustomerVo(int num) {
        int i = 0;
        try {
            i = customerMapper.deleteByPrimaryKey(num);
        } catch (Exception e) {
            System.out.println("填写数据有误");
        }
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateCustomerVo(CustomerVo customerVo) {
        Customer customer = JSONObject.parseObject(JSONObject.toJSONString(customerVo), Customer.class);
        int i = 0;
        try {
             i= customerMapper.updateByPrimaryKeySelective(customer);
        } catch (Exception e) {
            System.out.println("填写数据有误");
        }

        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public CustomerVo getCustomerBynNum(int num) {
        Customer customer = customerMapper.selectByPrimaryKey(num);
        CustomerVo customerVo = JSONObject.parseObject(JSONObject.toJSONString(customer), CustomerVo.class);
        return customerVo;
    }

    @Override
    public Integer countAllCustomer() {
        int i = customerMapper.countAllCustomer();
        return i;
    }

    @Override
    public Page<CustomerVo> allCustomerVo(int page, int limit) {
        int count = customerMapper.countAllCustomer();
        PageHelper.startPage(page,limit);
        ArrayList<Customer> customers = customerMapper.allCustomer();
        System.out.println(customers.get(0));
/*        ArrayList<CustomerVo> customerVos = new ArrayList<>();
        Page<CustomerVo> customerVoPage = new Page<>(page, limit, count);
        for (Customer customer: customers) {
            CustomerVo customerVo = JSONObject.parseObject(JSONObject.toJSONString(customer), CustomerVo.class);
            customerVos.add(customerVo);
        }
        customerVoPage.setItems(customerVos);
        return customerVoPage;*/
        Page<CustomerVo> customerVoPage = new MyArrayListPageConvert().myPageConvert(page, limit, count, customers);
        return customerVoPage;
    }
}
