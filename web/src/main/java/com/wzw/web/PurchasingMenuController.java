package com.wzw.web;

import com.wzw.service.IPurchasingMenuService;
import com.wzw.utils.*;
import com.wzw.vo.PurchasingMenuVo;
import com.wzw.vo.TablePage;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description TODO
 * @Date 2019/4/8 11:11
 * @Created by wzw
 */
@RestController
@RequestMapping("/purchasingMenu")
public class PurchasingMenuController {
    @Resource
    IPurchasingMenuService purchasingMenuService;

    int limit = 10;

    @RequestMapping("/goAll")
    public ModelAndView aoAll(ModelMap modelMap){
/*        Page<PurchasingMenuVo> purchasingMenuVoPage = purchasingMenuService.allPurchasingMenu(1, limit);
        int[] minAndMax = SmallPage.minAndMax(1, purchasingMenuVoPage.getTotalPage());
        modelMap.addAttribute("purchasingMenuVoPage", purchasingMenuVoPage);
        modelMap.addAttribute("minAndMax", minAndMax);
        return new ModelAndView("purchasingMenuList");*/
        return new ModelAndView("purchasingMenu");
    }

    @GetMapping("/all")
    public TablePage<PurchasingMenuVo> allPurchasingMenu(@RequestParam int page, @RequestParam int limit, @RequestParam(required = false)String name){
        Page<PurchasingMenuVo> purchasingMenuVoPage = purchasingMenuService.allPurchasingMenu(page, limit);

        Integer total = purchasingMenuService.countAllPurchasingMenu();
        return new TablePage<>(total,purchasingMenuVoPage.getItems());

        //int page = 1;
       // Map<String, Object> hashMap = new HashMap<>();
      //  Page<PurchasingMenuVo> purchasingMenuVoPage = purchasingMenuService.allPurchasingMenu(page, limit);
       // int[] minAndMax = SmallPage.minAndMax(page, purchasingMenuVoPage.getTotalPage());

       // modelMap.addAttribute("purchasingMenuVoPage", purchasingMenuVoPage);
        //modelMap.addAttribute("minAndMax", minAndMax);
/*        ArrayList<PurchasingMenuVo> items = (ArrayList<PurchasingMenuVo>)purchasingMenuVoPage.getItems();

        PurchasingMenuVo purchasingMenuVo = items.get(0);
        Date purchasingDate = purchasingMenuVo.getPurchasingDate();
        System.out.println(purchasingDate);*/

       // return new ModelAndView("purchasingMenuList");
        //hashMap.put("date",purchasingMenuVoPage);
       // return hashMap;
       //return new MyRespon(purchasingMenuService.countAllPurchasingMenu(),(ArrayList)purchasingMenuService.allPurchasingMenu(page,limit).getItems());
    }

    @GetMapping("/allInformation")
    public TablePage<PurchasingMenuVo> allInformation(){
        Page<PurchasingMenuVo> purchasingMenuVoPage = purchasingMenuService.allPurchasingMenu(1, 1000000);

        Integer total = purchasingMenuService.countAllPurchasingMenu();
        return new TablePage<>(total,purchasingMenuVoPage.getItems());
 }

    @RequestMapping("/goAdd")
    public ModelAndView aoAdd(){
      return new ModelAndView("purchasingMenuAdd");
    }

    @PostMapping("/add")
    public Map<String, Object> addPurchasingMenu(PurchasingMenuVo purchasingMenuVo){
        Map<String, Object> hashMap = new HashMap<>();
        Boolean savePurchasingMenu = purchasingMenuService.savePurchasingMenu(purchasingMenuVo);
        hashMap.put("savePurchasingMenu",savePurchasingMenu);
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
            deleteAll = purchasingMenuService.deletePurchasingMenu(i);
        }
        hashMap.put("deleteAll",deleteAll);
        return hashMap;
    }


    @DeleteMapping("/delete/{id}")
    public Boolean deletePurchasingMenu(@PathVariable int id){
        Boolean delete = purchasingMenuService.deletePurchasingMenu(id);
//        Map<String, Object> hashMap = new HashMap<>();
//        hashMap.put("deletePurchasingMenu",deletePurchasingMenu);
        return delete;
    }

    @PutMapping("/update")
    public Map<String, Object> updatePurchasingMenu(@RequestBody  PurchasingMenuVo purchasingMenuVo){
        Boolean updatePurchasingMenu = purchasingMenuService.updatePurchasingMenu(purchasingMenuVo);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("update",updatePurchasingMenu);
        return hashMap;
    }

    @GetMapping("/getPurchasingMenuById/{id}")
    public MyRespon getPurchasingMenuById(@PathVariable int id){
        PurchasingMenuVo purchasingMenuById = purchasingMenuService.getPurchasingMenuById(id);
        System.out.println(purchasingMenuById.getPurchasingDate());
        return MyGetObjectById.myRespon(purchasingMenuById);
    }
}
