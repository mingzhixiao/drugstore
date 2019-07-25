package com.wzw.web;

import com.wzw.service.ICustomerService;
import com.wzw.utils.MySplitJudge;
import com.wzw.utils.Page;
import com.wzw.utils.SmallPage;
import com.wzw.vo.CustomerVo;
import com.wzw.vo.TablePage;
import io.swagger.annotations.Api;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: drugstore
 * @description: 顾客信息的控制操作
 * @author: wzw
 * @create: 2019-03-27 14:09
 */
@Api(tags = "顾客Api")
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private ICustomerService customerService;

    int limit = 10;
    /**
     * @description 来到顾客信息显示界面
     * @param
     * @throws 
     * @return 
     */
  @RequestMapping("/goAll")
    public ModelAndView goCustomer(/*ModelMap modelMap*/){
      return  new ModelAndView("customer");
    }



    /**
     * @description 来到顾客信息添加页面
     * @param
     * @throws
     * @return
     */
    @RequestMapping("/goAdd")
    public ModelAndView goAdd(){
        ModelAndView modelAndView = new ModelAndView("customerAdd");
        return modelAndView;
    }


    /**
     * @description 添加顾客信息，修改顾客信息
     * @param
     * @throws
     * @return
     */
    @PostMapping("/add")
    public Boolean add(CustomerVo customerVo){
        customerVo.setStatus(1);
        Boolean result = customerService.insertCustomerVo(customerVo);
        System.out.println(result);
        return result;
    }


    @PutMapping("/update")
    public Map<String,Object> update(@RequestBody CustomerVo customerVo){
        Map<String, Object> resultMap = new HashMap<>();
            Boolean result = customerService.updateCustomerVo(customerVo);
            resultMap.put("update",result);
        return resultMap;
    }

    @DeleteMapping("/deleteAll")
    public Map<String, Object> deleteAll(String ids){
        Map<String, Object> hashMap = new HashMap<>();
        String[] id_s = MySplitJudge.judge(ids);
        Boolean deleteAll =null;
        for (String id : id_s)
        {
            int i = Integer.parseInt(id);
            deleteAll = customerService.deleteCustomerVo(i);
        }
        hashMap.put("deleteAll",deleteAll);
        return hashMap;
    }

    /**
     * 根据id来删除顾客信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable("id") int id){
        Boolean deleteCustomerVo = customerService.deleteCustomerVo(id);
        return deleteCustomerVo;
    }

    @GetMapping("/all")
    public TablePage<CustomerVo> all(@RequestParam int page, @RequestParam int limit, @RequestParam(required = false) String name){
        Page<CustomerVo> customerVoPage = customerService.allCustomerVo(page, limit);
        Integer total = customerService.countAllCustomer();
        return new TablePage<>(total,customerVoPage.getItems());
    }

    @GetMapping("/allInformation")
    public TablePage<CustomerVo> all(){
        Integer total = customerService.countAllCustomer();
        Page<CustomerVo> customerVoPage = customerService.allCustomerVo(1, total);
        return new TablePage<>(total,customerVoPage.getItems());
    }

    @GetMapping("getEmployeeByID/{id}")
    public Map<String, Object> getCustomerById(@PathVariable int id){
        CustomerVo customerBynNum = customerService.getCustomerBynNum(id);
        Map<String, Object> hashmap = new HashMap<>();
        hashmap.put("customer",customerBynNum);
        return hashmap;
    }


}
