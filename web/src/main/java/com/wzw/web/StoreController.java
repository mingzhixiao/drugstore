package com.wzw.web;

import com.wzw.service.IStoreService;
import com.wzw.utils.MyRespon;
import com.wzw.utils.MySplitJudge;
import com.wzw.utils.Page;
import com.wzw.vo.StoreVo;
import com.wzw.vo.TablePage;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/store")
public class StoreController {
    @Resource
    IStoreService storeService;
    int limit = 10;

    @RequestMapping("/goAll")
    public ModelAndView goStore(ModelMap modelMap){
/*        Page<StoreVo> storeVoPage = storeService.allStore(1, limit);
        int[] minAndMax = SmallPage.minAndMax(1, storeVoPage.getTotalPage());
        modelMap.addAttribute("storeVoPage",storeVoPage);
        modelMap.addAttribute("minAndMax",minAndMax);
        //MyRespon myRespon = new MyRespon(storeService.countAllStore(), (ArrayList) storeVoPage.getItems());
        return new ModelAndView("storeList");*/
        return new ModelAndView("store");
    }

    @GetMapping("/all")
    public TablePage<StoreVo> allStore(@RequestParam int page, @RequestParam int limit, @RequestParam(required = false)String name){
/*        //Map<String, Object> hashMap = new HashMap<>();
        Page<StoreVo> storeVoPage = storeService.allStore(page, limit);
        int[] minAndMax = SmallPage.minAndMax(page, storeVoPage.getTotalPage());
        modelMap.addAttribute("storeVoPage",storeVoPage);
        modelMap.addAttribute("minAndMax",minAndMax);
        //MyRespon myRespon = new MyRespon(storeService.countAllStore(), (ArrayList) storeVoPage.getItems());
        return new ModelAndView("storeList");*/
        Page<StoreVo> storeVoPage = storeService.allStore(page, limit);
        List<StoreVo> items = storeVoPage.getItems();
        Integer total = storeService.countAllStore();
        return new TablePage<>(total,items);
    }


    @GetMapping("/allInformation")
    public TablePage<StoreVo> allInformation(){
        Integer total = storeService.countAllStore();
        Page<StoreVo> storeVoPage = storeService.allStore(1, total);
        List<StoreVo> items = storeVoPage.getItems();
        return new TablePage<>(total,items);
    }

    @RequestMapping("/goAdd")
    public ModelAndView goAdd(){
        ModelAndView modelAndView = new ModelAndView("storeAdd");
        return modelAndView;
    }


    @PostMapping("/add")
    public Map<String, Object> add(StoreVo storeVo){
        Map<String,Object> hashMap = new HashMap<>();
        Boolean insertStore = storeService.insertStore(storeVo);
        hashMap.put("insertStore",insertStore);
        return hashMap;
    }





    @PutMapping("/update")
    public Map<String, Object> goUpdate(@RequestBody StoreVo storeVo){
        Map<String,Object> hashMap = new HashMap<>();
        Boolean updateStore = storeService.updateStore(storeVo);
        hashMap.put("update",updateStore);
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
            deleteAll =storeService.deleteStore(i);
        }
        hashMap.put("deleteAll",deleteAll);
        return hashMap;
    }

    @DeleteMapping("/delete/{medicineId}")
    public Boolean deleteStore(@PathVariable int medicineId){
       /* Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("deleteStore",storeService.deleteStore(medicineId));*/
        Boolean delete = storeService.deleteStore(medicineId);
        return delete;
    }

    @GetMapping("/getStoreById/{id}")
    public MyRespon getById(@PathVariable int id){
        StoreVo storeByNum = storeService.getStoreByNum(id);
        if (storeByNum == null){
            return new MyRespon(0,null);
        }
        else {
            ArrayList<StoreVo> storeVos = new ArrayList<>();
            storeVos.add(storeByNum);
            return new MyRespon(1,storeVos);
        }

    }

}





