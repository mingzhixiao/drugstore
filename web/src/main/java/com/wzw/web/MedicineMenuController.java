package com.wzw.web;

import com.wzw.service.IMedicineMenuService;
import com.wzw.utils.*;
import com.wzw.vo.MedicineMenuVo;
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
 * @Date 2019/4/8 10:50
 * @Created by wzw
 */
@RestController
@RequestMapping("/medicineMenu")
public class MedicineMenuController {
    @Resource
    IMedicineMenuService medicineMenuService;

    int limit = 10;

    @RequestMapping("/goAll")
    public ModelAndView goAll(ModelMap modelMap){
/*        Page<MedicineMenuVo> medicineMenuVoPage = medicineMenuService.allMedicineMenu(1, limit);
        int[] minAndMax = SmallPage.minAndMax(1, medicineMenuVoPage.getTotalPage());
        modelMap.addAttribute("medicineMenuVoPage",medicineMenuVoPage);
        modelMap.addAttribute("minAndMax",minAndMax);
        return new ModelAndView("medicineMenuList");*/
        return new ModelAndView("medicineMenu");
    }

    @GetMapping("/all")
    public TablePage <MedicineMenuVo> allMedicineMenu(@RequestParam int page, @RequestParam int limit, @RequestParam(required = false) String name){
/*        Map<String, Object> hashMap = new HashMap<>();
        Page<MedicineMenuVo> medicineMenuVoPage = medicineMenuService.allMedicineMenu(page, limit);
        int[] minAndMax = SmallPage.minAndMax(page, medicineMenuVoPage.getTotalPage());
        modelMap.addAttribute("medicineMenuVoPage",medicineMenuVoPage);
        modelMap.addAttribute("minAndMax",minAndMax);
        return new ModelAndView("medicineMenuList");*/
       // return  new MyRespon(medicineMenuService.countAllMedicineMenu(),(ArrayList)medicineMenuService.allMedicineMenu(page,limit).getItems());
        Page<MedicineMenuVo> medicineMenuVoPage = medicineMenuService.allMedicineMenu(page, limit);
        Integer total = medicineMenuService.countAllMedicineMenu();
        return  new TablePage<>(total,medicineMenuVoPage.getItems());
    }


    @GetMapping("/allInformation")
    public TablePage <MedicineMenuVo> allMedicineMenu(){
/*        Map<String, Object> hashMap = new HashMap<>();
        Page<MedicineMenuVo> medicineMenuVoPage = medicineMenuService.allMedicineMenu(page, limit);
        int[] minAndMax = SmallPage.minAndMax(page, medicineMenuVoPage.getTotalPage());
        modelMap.addAttribute("medicineMenuVoPage",medicineMenuVoPage);
        modelMap.addAttribute("minAndMax",minAndMax);
        return new ModelAndView("medicineMenuList");*/
        // return  new MyRespon(medicineMenuService.countAllMedicineMenu(),(ArrayList)medicineMenuService.allMedicineMenu(page,limit).getItems());
        Page<MedicineMenuVo> medicineMenuVoPage = medicineMenuService.allMedicineMenu(1, 1000000);
        Integer total = medicineMenuService.countAllMedicineMenu();
        return  new TablePage<>(total,medicineMenuVoPage.getItems());
    }





    @RequestMapping("/goAdd")
    public ModelAndView goAdd(){
       return new ModelAndView("medicineMenuAdd");
    }

    @PostMapping("/add")
    public Map<String, Object> addMedicineMenu(MedicineMenuVo medicineMenuVo){
        Boolean saveMedicineMenu = medicineMenuService.saveMedicineMenu(medicineMenuVo);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("saveMedicineMenu",saveMedicineMenu);
        return hashMap;
    }

    @PutMapping("/update")
    public Map<String, Object> updateMedicineMenu(@RequestBody MedicineMenuVo medicineMenuVo){
        Boolean update = medicineMenuService.update(medicineMenuVo);
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
            deleteAll = medicineMenuService.deleteMedicineMenu(i);
        }
        hashMap.put("deleteAll",deleteAll);
        return hashMap;
    }



    @DeleteMapping("/delete/{number}")
    public boolean deleteMedicineMenu(@PathVariable int number){
        Boolean delete = medicineMenuService.deleteMedicineMenu(number);
        return delete;
    }

    @GetMapping("/getMedicineMenuById/{number}")
    public MedicineMenuVo getMedicineMenuById(@PathVariable int number){
        MedicineMenuVo medicineByNum = medicineMenuService.getMedicineByNum(number);
        return medicineByNum;
    }
}
