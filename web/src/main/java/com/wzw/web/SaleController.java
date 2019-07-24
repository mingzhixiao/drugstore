package com.wzw.web;

import com.wzw.service.ISaleService;
import com.wzw.utils.*;
import com.wzw.vo.SaleVo;
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
 * @Date 2019/4/8 14:21
 * @Created by wzw
 */
@RestController
@RequestMapping("/sale")
public class SaleController {
    @Resource
    ISaleService saleService;
    int limit = 10;

    @RequestMapping("/goAll")
    public ModelAndView goAll(ModelMap modelMap){
        return new ModelAndView("sale");
/*        Page<SaleVo> saleVoPage = saleService.allSale(1, limit);
        int[] minAndMax = SmallPage.minAndMax(1, saleVoPage.getTotalPage());
        modelMap.addAttribute("saleVoPage",saleVoPage);
        modelMap.addAttribute("minAndMax",minAndMax);
        return new ModelAndView("saleList");*/
    }

    @GetMapping("/all")
    public TablePage<SaleVo> allSale(@RequestParam int page, @RequestParam int limit, @RequestParam(required = false)String name){
 /*       Map<String, Object> hashMap = new HashMap<>();
        Page<SaleVo> saleVoPage = saleService.allSale(page, limit);
        int[] minAndMax = SmallPage.minAndMax(page, saleVoPage.getTotalPage());
        modelMap.addAttribute("saleVoPage",saleVoPage);
        modelMap.addAttribute("minAndMax",minAndMax);
        return new ModelAndView("saleList");*/
        //return new MyRespon(saleService.countAllSale(),(ArrayList)saleService.allSale(page,limit).getItems());
        Page<SaleVo> saleVoPage = saleService.allSale(page, limit);
        Integer total = saleService.countAllSale();
        return new TablePage<>(total,saleVoPage.getItems());
    }


    @GetMapping("/allInformation")
    public TablePage<SaleVo> allInformation(){
        Integer total = saleService.countAllSale();
        Page<SaleVo> saleVoPage = saleService.allSale(1, total);
        return new TablePage<>(total,saleVoPage.getItems());
    }


    @RequestMapping("/goAdd")
    public ModelAndView goAdd(){
        return new ModelAndView("saleAdd");
    }

    @PostMapping("/add")
    public Map<String, Object> addSale(SaleVo saleVo){
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("add",saleService.saveSale(saleVo));
        return hashMap;
    }

    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody SaleVo saleVo){
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("update",saleService.updateSale(saleVo));
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
            deleteAll = saleService.deleteSale(i);
        }
        hashMap.put("deleteAll",deleteAll);
        return hashMap;
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable int id){
/*        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("delete",saleService.deleteSale(id));*/
        Boolean delete = saleService.deleteSale(id);
        return delete;
    }

    @GetMapping("/getSaleById/{id}")
    public MyRespon getSaleById(@PathVariable  int id){
        SaleVo saleById = saleService.getSaleById(id);
        return MyGetObjectById.myRespon(saleById);
    }
}
