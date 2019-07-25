package com.wzw.web;

import com.wzw.service.ISaleMenuService;
import com.wzw.utils.*;
import com.wzw.vo.SaleMenuVo;
import com.wzw.vo.TablePage;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2019/4/8 14:05
 * @Created by wzw
 */
@RestController
@RequestMapping("/saleMenu")
public class SaleMenuController {
    @Resource
    ISaleMenuService saleMenuService;
    int limit = 10;

    @RequestMapping("/goAll")
    public ModelAndView goAll(ModelMap modelMap){
/*        Page<SaleMenuVo> saleMenuVoPage = saleMenuService.allSaleMenu(1, limit);
        int[] minAndMax = SmallPage.minAndMax(1, saleMenuVoPage.getTotalPage());
        modelMap.addAttribute("saleMenuVoPage",saleMenuVoPage);
        modelMap.addAttribute("minAndMax",minAndMax);
        return new ModelAndView("saleMenuList");*/
        return new ModelAndView("saleMenu");
    }

    @GetMapping("/all")
    public TablePage <SaleMenuVo> allSaleMenu(@RequestParam int page, @RequestParam int limit, @RequestParam(required = false)String name){
/*        Map<String, Object> hashMap= new HashMap<>();
        Page<SaleMenuVo> saleMenuVoPage = saleMenuService.allSaleMenu(page, limit);
        int[] minAndMax = SmallPage.minAndMax(page, saleMenuVoPage.getTotalPage());
        modelMap.addAttribute("saleMenuVoPage",saleMenuVoPage);
        modelMap.addAttribute("minAndMax",minAndMax);
        return new ModelAndView("saleMenuList");*/
        //return new MyRespon(saleMenuService.countAllSaleMenu(),(ArrayList)saleMenuService.allSaleMenu(page, limit).getItems());
        Page<SaleMenuVo> saleMenuVoPage = saleMenuService.allSaleMenu(page, limit);
        List<SaleMenuVo> items = saleMenuVoPage.getItems();
        Integer total = saleMenuService.countAllSaleMenu();
        return new TablePage<>(total,items);

    }
    @GetMapping("/allInformation")
    public TablePage <SaleMenuVo> allInformation(){
        Integer total = saleMenuService.countAllSaleMenu();
        Page<SaleMenuVo> saleMenuVoPage = saleMenuService.allSaleMenu(1, total);
        List<SaleMenuVo> items = saleMenuVoPage.getItems();
        return new TablePage<>(total,items);
    }

    @RequestMapping("/goAdd")
    public ModelAndView goAdd(){
        return new ModelAndView("saleMenuAdd");
    }

    @PostMapping("/add")
    public Map<String , Object> addSaleMenu(SaleMenuVo saleMenuVo){
        Boolean save = saleMenuService.saveSaleMenu(saleMenuVo);
        Map<String, Object> hashMap= new HashMap<>();
        hashMap.put("save",save);
        return hashMap;
    }


    @PutMapping("/update")
    public Map<String , Object> updateSaleMenu(@RequestBody SaleMenuVo saleMenuVo){
        Boolean update = saleMenuService.updateSaleMenu(saleMenuVo);
        Map<String, Object> hashMap= new HashMap<>();
        hashMap.put("update",update);
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
            deleteAll = saleMenuService.deleteSaleMenu(i);
        }
        hashMap.put("deleteAll",deleteAll);
        return hashMap;
    }


    @DeleteMapping("/delete/{id}")
    public Boolean deleteSaleMenu(@PathVariable int id){
        Boolean delete = saleMenuService.deleteSaleMenu(id);
/*        Map<String, Object> hashMap= new HashMap<>();
        hashMap.put("delete",delete);*/
        return delete;
    }

    @GetMapping("/getSaleMenuById/{id}")
    public MyRespon getSaleMenuById(@PathVariable int id){
        SaleMenuVo saleMenuById = saleMenuService.getSaleMenuById(id);
        return MyGetObjectById.myRespon(saleMenuById);
    }

}
