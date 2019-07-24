package com.wzw.web;

import com.wzw.service.IIncomeService;
import com.wzw.utils.MyRespon;
import com.wzw.utils.MySplitJudge;
import com.wzw.utils.Page;
import com.wzw.utils.SmallPage;
import com.wzw.vo.IncomeVo;
import com.wzw.vo.TablePage;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2019/4/8 10:10
 * @Created by wzw
 */
@RequestMapping("/income")
@RestController
public class IncomeController {
    @Resource
    private IIncomeService incomeService;
    int limit = 10;

    @GetMapping("all")
    public TablePage<IncomeVo> all(@RequestParam int page, @RequestParam int limit, @RequestParam(required = false) String name){
/*        Page<IncomeVo> incomeVoPage = incomeService.allIncome(1, limit);
        System.out.println(incomeVoPage.getItems().size());
        int[] minAndMax = SmallPage.minAndMax(1, incomeVoPage.getTotalPage());
        modelMap.addAttribute("incomeVoPage",incomeVoPage);
        modelMap.addAttribute("minAndMax",minAndMax);
        return new ModelAndView("incomeList");*/




        Page<IncomeVo> incomeVoPage = incomeService.allIncome(page, limit);
        Integer total = incomeService.countAllIncome();
        return new TablePage<>(total,incomeVoPage.getItems());
    }

    @GetMapping("allIncome")
    public TablePage<IncomeVo> all(){
        Page<IncomeVo> incomeVoPage = incomeService.allIncome(1, 10000);
        Integer total = incomeService.countAllIncome();
        return new TablePage<>(total,incomeVoPage.getItems());
    }





    @RequestMapping("/goAll")
    public ModelAndView goAll(){
        return new ModelAndView("income");
        //Map<String, Object> hashmap = new HashMap<>();
//        Page<IncomeVo> incomeVoPage = incomeService.allIncome(page, limit);
//        System.out.println(incomeVoPage.getItems().size());
//        int[] minAndMax = SmallPage.minAndMax(page, incomeVoPage.getTotalPage());
//        modelMap.addAttribute("incomeVoPage",incomeVoPage);
//        modelMap.addAttribute("minAndMax",minAndMax);
//        return new ModelAndView("incomeList");
       // return new MyRespon(incomeService.countAllIncome(),(ArrayList) incomeService.allIncome(page,limit).getItems());
    }


    @RequestMapping("/goAdd")
    public ModelAndView goAdd(){
        ModelAndView modelAndView = new ModelAndView("incomeAdd");
        return modelAndView;
    }

    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody IncomeVo incomeVo){
        Map<String, Object> hashMap = new HashMap<>();
            Boolean updateIncome = incomeService.updateIncome(incomeVo);
            hashMap.put("update",updateIncome);
            return hashMap;
    }

    @PostMapping("/add")
    public Map<String, Object> add(IncomeVo incomeVo){
        Map<String, Object> hashMap = new HashMap<>();
            Boolean saveIncome = incomeService.saveIncome(incomeVo);
            hashMap.put("saveIncome",saveIncome);
            return hashMap;
    }


    @DeleteMapping("/deleteAll")
    public Map<String, Object> deleteAll(String ids){
        Map<String, Object> hashMap = new HashMap<>();
        String[] id_s = MySplitJudge.judge(ids);
        Boolean deleteAll =null;
        for (String id : id_s)
        {
            int i = Integer.parseInt(id);
            deleteAll = incomeService.deleteIncome(i);
        }
        hashMap.put("deleteAll",deleteAll);
        return hashMap;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteIncome(@PathVariable int id){
       Boolean deleteIncome = incomeService.deleteIncome(id);
       Map<String, Object> hashMap = new HashMap<>();
       hashMap.put("delete",deleteIncome);
       return hashMap;
    }

    @GetMapping("/getIncomeById/{id}")
    public MyRespon getIncomeById(@PathVariable  int id){
        if (incomeService.getIncomeById(id) != null){
            ArrayList<IncomeVo> incomeVos = new ArrayList<>();
            incomeVos.add(incomeService.getIncomeById(id));
            return new MyRespon(1,incomeVos);
        }
        else {
            return new MyRespon(1,null);
        }
    }







}
