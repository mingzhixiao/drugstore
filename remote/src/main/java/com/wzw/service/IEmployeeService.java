package com.wzw.service;


import com.wzw.utils.Page;
import com.wzw.vo.EmployeeVo;

import java.util.ArrayList;

public interface IEmployeeService {

    Boolean updateEmployeeByID(EmployeeVo employeeVo);

    Boolean insertEmployee(EmployeeVo employeeVo);

    Boolean deleteEmployeeByID(int id);

    //根据ID查询
    EmployeeVo getEmployeeByID(int id);


    Integer count();
    Page<EmployeeVo> getAllEmployee(int page, int limit);


    ArrayList<EmployeeVo> getEmployeeByName(String username);
}
