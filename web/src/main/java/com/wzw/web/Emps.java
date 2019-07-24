package com.wzw.web;

import com.wzw.service.IEmployeeService;
import com.wzw.utils.Page;
import com.wzw.utils.SmallPage;
import com.wzw.vo.EmployeeVo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2019/4/8 9:58
 * @Created by wzw
 */
@RestController
@RequestMapping("/emps")
public class Emps {
    @Resource
    IEmployeeService employeeService;


    @RequestMapping("/one")
    public ModelAndView one(){
        return new ModelAndView("emps");
    }


   @RequestMapping("/two")
    public ModelAndView two(){
        return new ModelAndView("emps2");
    }


    @RequestMapping("/three")
    public ModelAndView three(){
        return new ModelAndView("emps3");
    }

    @RequestMapping("/four")
    public ModelAndView four(){
        return new ModelAndView("emps4");
    }
    @RequestMapping("/clock")
    public ModelAndView clock(){
        return new ModelAndView("clock");
    }






    @GetMapping("/all")
    public List<EmployeeVo> allEmployee(){
        Map<String, Object> hashMap = new HashMap<>();
        Page<EmployeeVo> allEmployee = employeeService.getAllEmployee(1, 100);
        List<EmployeeVo> items = allEmployee.getItems();
        hashMap.put("",items);
        hashMap.put("total",2);
        hashMap.put("totalNotFiltered",2);
        return items;
        //return hashMap;
    }

    @GetMapping("/hh")
    public Map<String,Object> h(@RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String name){
        Map<String, Object> hashMap = new HashMap<>();
        Page<EmployeeVo> allEmployee = employeeService.getAllEmployee(page, limit);
        List<EmployeeVo> items = allEmployee.getItems();
        //System.out.println("name"+name);
        //System.out.println(name.length());
        if (name != ""){
            System.out.println(true);
            items = employeeService.getEmployeeByName(name);
        }
        hashMap.put("rows",items);
        hashMap.put("total",employeeService.count());
       // return items;
        return hashMap;
    }









}
