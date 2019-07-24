package com.wzw.service;

import java.util.ArrayList;
import java.util.List;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;

import com.wzw.entity.Employee;
import com.wzw.mapper.EmployeeMapper;
import com.wzw.utils.MyArrayListPageConvert;
import com.wzw.utils.Page;
import com.wzw.vo.EmployeeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.wzw.utils.MyTrueAndFalse.myTrueAndFalse;

@Service("employeeService")
public class IEmployeeServiceImpl implements IEmployeeService {
    @Resource
    EmployeeMapper employeeMapper;

    @Override
    public Boolean updateEmployeeByID(EmployeeVo employeeVo) {
        Employee employee = JSONObject.parseObject(JSONObject.toJSONString(employeeVo), Employee.class);
        int i = 0;
        try {
            i = employeeMapper.updateByPrimaryKeySelective(employee);
        } catch (Exception e) {
            System.out.println("输入有误");
        }
        return myTrueAndFalse(i);
    }

    @Override
    public Boolean insertEmployee(EmployeeVo employeeVo) {
        Employee employee = JSONObject.parseObject(JSONObject.toJSONString(employeeVo), Employee.class);
        int i = 0;
        try {
            i = employeeMapper.insertSelective(employee);
        } catch (Exception e) {

        }
        return myTrueAndFalse(i);
    }

    @Override
    public Boolean deleteEmployeeByID(int id) {
        int i = employeeMapper.deleteByPrimaryKey(id);
        return myTrueAndFalse(i);
    }

    @Override
    public EmployeeVo getEmployeeByID(int id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        EmployeeVo employeeVo = JSONObject.parseObject(JSONObject.toJSONString(employee), EmployeeVo.class);
        return employeeVo;
    }

    @Override
    public Integer count() {
        int i = employeeMapper.countAllEmployee();
        return i;
    }

    @Override
    public Page<EmployeeVo> getAllEmployee(int page, int limit) {
        System.out.println("我是三号");

        int count = employeeMapper.countAllEmployee();
        PageHelper.startPage(page,limit);
        ArrayList<Employee> employees = employeeMapper.allEmployee();
        Page<EmployeeVo> employeeVoPage = new MyArrayListPageConvert().myPageConvert(page, limit, count, employees);
        //System.out.println(employeeVoPage.getItems().get(0).getEmail());
        return employeeVoPage;
    }

    @Override
    public ArrayList<EmployeeVo> getEmployeeByName(String username) {
        System.out.println(username);
        ArrayList<Employee> employees = employeeMapper.getEmployeeByName(username);
        ArrayList<EmployeeVo> employeeVos = new ArrayList<>();
        for (Employee employee: employees){
           EmployeeVo employeeVo = JSONObject.parseObject(JSONObject.toJSONString(employee), EmployeeVo.class);
            employeeVos.add(employeeVo);
       }
        return employeeVos;
    }
}
