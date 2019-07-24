package com.wzw.web;

import com.wzw.service.IPurchasingService;
import com.wzw.utils.*;
import com.wzw.vo.PurchasingVo;
import com.wzw.vo.TablePage;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2019/4/8 11:55
 * @Created by wzw
 */

@RestController
@RequestMapping("/purchasing")
public class PurchasingController {
    @Resource
    IPurchasingService purchasingService;
    int limit = 10;


    @RequestMapping("/goAll")
    public ModelAndView goAll(ModelMap modelMap){
/*        Page<PurchasingVo> purchasingVoPage = purchasingService.allPurchasing(1, limit);
        int[] minAndMax = SmallPage.minAndMax(1, purchasingVoPage.getTotalPage());
        modelMap.addAttribute("purchasingVoPage",purchasingVoPage);
        modelMap.addAttribute("minAndMax",minAndMax);
        return new ModelAndView("purchasingList");*/
        return new ModelAndView("purchasing");
    }

    @GetMapping("/all")
    public TablePage<PurchasingVo> allPurchasing(@RequestParam int page, @RequestParam int limit, @RequestParam(required = false)String name){
        //Map<String, Object> hashMap = new HashMap<>();
        Page<PurchasingVo> purchasingVoPage = purchasingService.allPurchasing(page, limit);
/*        int[] minAndMax = SmallPage.minAndMax(page, purchasingVoPage.getTotalPage());
        modelMap.addAttribute("purchasingVoPage",purchasingVoPage);
        modelMap.addAttribute("minAndMax",minAndMax);
        return new ModelAndView("purchasingList");*/
        //return new MyRespon(purchasingService.countAllPurchasing(),(ArrayList)purchasingService.allPurchasing(page,limit).getItems());
        Integer total = purchasingService.countAllPurchasing();
        return new TablePage<>(total,purchasingVoPage.getItems());
    }


    @GetMapping("/allInformation")
    public TablePage<PurchasingVo> allInformation(){
        Integer total = purchasingService.countAllPurchasing();
        Page<PurchasingVo> purchasingVoPage = purchasingService.allPurchasing(1, total);
        return new TablePage<>(total,purchasingVoPage.getItems());
    }

    @RequestMapping("/goAdd")
    public ModelAndView goAdd(){
        return new ModelAndView("purchasingAdd");
    }

    @PostMapping("/add")
    public Map<String, Object> add(PurchasingVo purchasingVo){
        Boolean save = purchasingService.savePurchasing(purchasingVo);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("save",save);
        return hashMap;
    }

    @PutMapping("/update")
    public Map<String, Object> updatePurchasing(@RequestBody PurchasingVo purchasingVo){
        Boolean update = purchasingService.updatePurchasing(purchasingVo);
        Map<String, Object> hashMap = new HashMap<>();
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
            deleteAll =purchasingService.deletePurchasing(i);
        }
        hashMap.put("deleteAll",deleteAll);
        return hashMap;
    }


    @DeleteMapping("/delete/{id}")
    public Boolean deletePurchasing(@PathVariable int id){
        Boolean delete = purchasingService.deletePurchasing(id);
/*        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("save",delete);*/
        return delete;
    }

    @GetMapping("/getPurchasingById/{id}")
    public MyRespon getPurchasingById(@PathVariable int id){
        PurchasingVo purchasing = purchasingService.getMedicineById(id);
        return MyGetObjectById.myRespon(purchasing);
    }

}
