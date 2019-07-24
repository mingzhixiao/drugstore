package com.wzw.web;

import com.wzw.service.IEmployeeService;
import com.wzw.service.feature.EmailService;
import com.wzw.service.feature.IValidateCodeService;
import com.wzw.utils.MyUUId;
import com.wzw.utils.Page;
import com.wzw.utils.SmallPage;
import com.wzw.vo.EmployeeVo;
import com.wzw.vo.ValidateCodeVo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    IEmployeeService employeeService;

    @Resource
    IValidateCodeService validateCodeService;

    @Resource
    EmailService emailService;

    int limit = 10;

    @RequestMapping("/goAll")
    public ModelAndView goAll(ModelMap modelMap){
        Page<EmployeeVo> allEmployee = employeeService.getAllEmployee(1, limit);
        int[] minAndMax = SmallPage.minAndMax(1, allEmployee.getTotalPage());
        modelMap.addAttribute("allEmployee",allEmployee);
        modelMap.addAttribute("minAndMax",minAndMax);
        //MyRespon myRespon = new MyRespon(employeeService.count(),(ArrayList) allEmployee.getItems());
        return new ModelAndView("employeeList");
    }

    @GetMapping("/all")
    public ModelAndView allEmployee(int page, ModelMap modelMap){
        Page<EmployeeVo> allEmployee = employeeService.getAllEmployee(page, limit);
        int[] minAndMax = SmallPage.minAndMax(page, allEmployee.getTotalPage());
        modelMap.addAttribute("allEmployee",allEmployee);
        modelMap.addAttribute("minAndMax",minAndMax);
        //MyRespon myRespon = new MyRespon(employeeService.count(),(ArrayList) allEmployee.getItems());
        return new ModelAndView("employeeList");
    }


    @RequestMapping("/goAdd")
    public ModelAndView goAdd(){
        ModelAndView modelAndView = new ModelAndView("employeeAdd");
        return modelAndView;
    }
    @PostMapping("/add")
    public Map<String, Object> add(EmployeeVo employeeVo){
        Map<String, Object> hashMap = new HashMap<>();
       /* employeeVo.setId(MyUUId.getMyUUid());*/
        Boolean insertEmployee = employeeService.insertEmployee(employeeVo);
        hashMap.put("insertEmployee",insertEmployee);
        return hashMap;
    }
    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody EmployeeVo employeeVo){
        Map<String, Object> hashMap = new HashMap<>();
        System.out.println("update"+employeeVo.getId());
        Boolean updateEmployeeByID = employeeService.updateEmployeeByID(employeeVo);
        hashMap.put("updateEmployeeByID",updateEmployeeByID);
        return hashMap;
    }

    @DeleteMapping("/deleteAll")
    public Map<String, Object> deleteAll(String ids){
        System.out.println("******ids="+ids);
        Map<String, Object> hashMap = new HashMap<>();
        String []  id_s =new String[ids.length()];
        if (ids.length() > 1){
            id_s = ids.split(",");
        } else {
            id_s[0] =ids;
        }
        Boolean deleteEmployeeByID =null;
        for (String id : id_s
             ) {
            int i = Integer.parseInt(id);
            deleteEmployeeByID = employeeService.deleteEmployeeByID(i);
            }
        hashMap.put("deleteEmployeeByID",deleteEmployeeByID);
        return hashMap;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable int id){
        Map<String, Object> hashMap = new HashMap<>();
        Boolean deleteEmployeeByID = employeeService.deleteEmployeeByID(id);
        hashMap.put("deleteEmployeeByID",deleteEmployeeByID);
        return hashMap;
    }

    @GetMapping("/getById/{id}")
    public Map<String, Object> getEmployeeById (@PathVariable int id){
        Map<String, Object> hashMap = new HashMap<>();
        if (employeeService.getEmployeeByID(id).getId() != null){
            System.out.println(id);
            ArrayList<EmployeeVo> employeeVos = new ArrayList<>();
            EmployeeVo employeeByID = employeeService.getEmployeeByID(id);
            hashMap.put("employee",employeeByID);
            employeeVos.add(employeeByID);
            return hashMap;
        }
        return null;
    }
    @RequestMapping("/modify")
    public Map<String, Object> modify(@RequestParam String password,@RequestParam String vCode, HttpSession session){
        System.out.println("password"+password);
        Map<String, Object> hashMap = new HashMap<>();
        EmployeeVo employee = (EmployeeVo)session.getAttribute("employee");
        if (vCode.equals(validateCodeService.getPasswordByEmail(employee.getEmail()))){
            employee.setPassword(password);
            employeeService.updateEmployeeByID(employee);
            hashMap.put("update",true);
            return hashMap;
        }
        hashMap.put("update",false);
        return hashMap;
    }

    @RequestMapping("/goModify")
    public ModelAndView goModify(){
        return new ModelAndView("modifyPersonalInformation");
    }


    @RequestMapping("/goModifyOneself")
    public ModelAndView goModifyOneself(){
        return new ModelAndView("modify");
    }

    @PutMapping("/updateOneself")
    public Map<String, Object> updateOneself(@RequestBody EmployeeVo employeeVo,HttpSession session){
        System.out.println(employeeVo.getEmail());
        Map<String, Object> hashMap = new HashMap<>();
        EmployeeVo employee = (EmployeeVo) session.getAttribute("employee");
        employeeVo.setPassword(employee.getPassword());
        employeeVo.setStatus(employee.getStatus());
        Boolean updateEmployeeByID = employeeService.updateEmployeeByID(employeeVo);
        session.setAttribute("employee",employeeVo);
        hashMap.put("update",updateEmployeeByID);
        return hashMap;
    }


}
