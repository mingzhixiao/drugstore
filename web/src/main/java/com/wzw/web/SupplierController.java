package com.wzw.web;

import com.wzw.service.ISupplierService;
import com.wzw.utils.*;
import com.wzw.vo.SupplierVo;
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
 * @Date 2019/4/8 14:34
 * @Created by wzw
 */

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Resource
    ISupplierService supplierService;
    int limit = 10;

    /**
     * 来到供应商信息页面
     * @param modelMap
     * @return
     */
    @RequestMapping("/goAll")
    public ModelAndView goAll(ModelMap modelMap){
/*        Page<SupplierVo> supplierVoPage = supplierService.allSupplier(1, limit);
        int[] minAndMax = SmallPage.minAndMax(1, supplierVoPage.getTotalPage());
        modelMap.addAttribute("supplierVoPage",supplierVoPage);
        modelMap.addAttribute("minAndMax",minAndMax);
        return new ModelAndView("supplierList");*/
        return new ModelAndView("supplier");
    }

    @GetMapping("/all")
    public TablePage<SupplierVo> allSupplier(int page, ModelMap modelMap){
        Page<SupplierVo> supplierVoPage = supplierService.allSupplier(page, limit);
        List<SupplierVo> items = supplierVoPage.getItems();
        Integer total = supplierService.countAllSupplier();
        return new TablePage<>(total,items);
    }

    @GetMapping("/allInformation")
    public TablePage<SupplierVo> allInformation(){
        Integer total = supplierService.countAllSupplier();
        Page<SupplierVo> supplierVoPage = supplierService.allSupplier(1, total);
        List<SupplierVo> items = supplierVoPage.getItems();
        return new TablePage<>(total,items);
    }



    @RequestMapping("/goAdd")
    public ModelAndView goAdd(){
        return new ModelAndView("supplierAdd");
    }


    @PostMapping("/add")
    public Map<String, Object> addSupplier(SupplierVo supplierVo){
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("add",supplierService.saveSupplier(supplierVo));
        return hashMap;
    }


    @PutMapping("/update")
    public Map<String, Object> updateSupplier(@RequestBody SupplierVo supplierVo){
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("update",supplierService.updateSupplier(supplierVo));
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
            deleteAll = supplierService.deleteSupplier(i);
        }
        hashMap.put("deleteAll",deleteAll);
        return hashMap;
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteSupplier(@PathVariable int id){
       /* Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("delete",supplierService.deleteSupplier(id));*/
        Boolean delete = supplierService.deleteSupplier(id);
        return delete;
    }

    @GetMapping("/getSupplierById/{id}")
    public MyRespon getSupplierById(@PathVariable int id){
        SupplierVo supplierById = supplierService.getSupplierById(id);
        return MyGetObjectById.myRespon(supplierById);
    }




}
