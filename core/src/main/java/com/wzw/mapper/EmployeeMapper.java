package com.wzw.mapper;

import com.wzw.entity.Employee;

import java.util.ArrayList;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    ArrayList<Employee> getEmployeeByName(String name);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    ArrayList<Employee> allEmployee();

    int countAllEmployee();
}