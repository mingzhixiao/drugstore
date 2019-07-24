package com.wzw.web;

import com.wzw.service.*;
import com.wzw.utils.*;
import com.wzw.vo.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/medicine")
@RestController
public class MedicineController {
    @Resource
    IMedicineService medicineService;

    @Resource
    IStoreService storeService;


    @Resource
    IIncomeService incomeService;


    @Resource
    IPurchasingService purchasingService;



    int limit = 10;

    @RequestMapping("/goAdd")
    public ModelAndView goAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("medicineAdd");
        return modelAndView;
    }


    @PostMapping("/add")
    public Map<String, Object> addMedicine(MedicineVo medicineVo){
        Boolean saveMedicine = medicineService.saveMedicine(medicineVo);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("success", saveMedicine);

        //添加库存药品的同时给定一个库存为-1的入库信息
        Integer medicineVoId = medicineVo.getId();
        Boolean addStore = storeService.inStore(medicineVoId);



/*        //添加一个收入为-1的记录
        IncomeVo incomeVo = new IncomeVo();
        StoreVo store = storeService.getStoreByNum(medicineVoId);
        //purchasingService.getMedicineById()
        Date productionDate = store.getProductionDate();
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month =new SimpleDateFormat("MM");*/



        return hashMap;
    }


    @PutMapping("/update")
    public Map<String, Object> updateMedicine(@RequestBody MedicineVo medicineVo){
        Boolean saveMedicine = medicineService.updateMedicine(medicineVo);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("update", saveMedicine);
        return hashMap;
    }


    @RequestMapping("/goAll")
    public ModelAndView goMedicine(/*ModelMap modelMap*/){
/*        Page<MedicineVo> medicineVoPage = medicineService.allMedicine(1, limit);
        int[] minAndMax = SmallPage.minAndMax(1, medicineVoPage.getTotalPage());
        modelMap.addAttribute("medicineVoPage",medicineVoPage);
        modelMap.addAttribute("minAndMax",minAndMax);
        return new ModelAndView("medicineList");*/
        return new ModelAndView("medicine");
    }

    @GetMapping("/all")
    public TablePage<MedicineVo> allMedicine(@RequestParam int page, @RequestParam int limit, @RequestParam(required = false) String name){
        Page<MedicineVo> medicineVoPage = medicineService.allMedicine(page, limit);
        Integer total = medicineService.countAllMedicine();
        return new TablePage<>(total,medicineVoPage.getItems());
    }

    @GetMapping("/allInformation")
    public TablePage<MedicineVo> allInformation(){
        Integer total = medicineService.countAllMedicine();
        Page<MedicineVo> medicineVoPage = medicineService.allMedicine(1, total);
        return new TablePage<>(total,medicineVoPage.getItems());
    }



    @DeleteMapping("/deleteAll")
    public Map<String, Object> deleteAll(String ids){
        Map<String, Object> hashMap = new HashMap<>();
        String[] id_s = MySplitJudge.judge(ids);
        Boolean deleteAll =null;
        for (String id : id_s)
        {
            int i = Integer.parseInt(id);
            deleteAll = medicineService.deleteMedicineById(i);
        }
        hashMap.put("deleteAll",deleteAll);
        return hashMap;
    }



    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable int id){
        Boolean delete = medicineService.deleteMedicineById(id);
        return delete;
    }

    @GetMapping("/getMedicineById/{id}")
    public MyRespon getMedicineById(@PathVariable int id){
        MedicineVo medicineById = medicineService.getMedicineById(id);
        System.out.println(medicineById.getMedicineMenuNumber());
        return MyGetObjectById.myRespon(medicineById);
    }

}
