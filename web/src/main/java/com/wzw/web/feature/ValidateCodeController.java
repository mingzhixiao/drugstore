
package com.wzw.web.feature;


import com.wzw.service.feature.EmailService;
import com.wzw.service.feature.IValidateCodeService;
import com.wzw.vo.EmployeeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@ResponseBody
@RequestMapping("/email")
@Controller
public class ValidateCodeController {
    @Resource
    IValidateCodeService validateCodeService;

    @Resource
    EmailService emailService;




    @RequestMapping("/sendCode")
    public Map<String, Object> sendCode(HttpSession session){
        StringBuilder code =new StringBuilder();
        Random random =new Random();
        for (int i = 0; i < 4 ; i++) {
            int index = random.nextInt(10);
            code.append(index);
        }


        Map<String, Object> hashMap = new HashMap<>();
        EmployeeVo employee = (EmployeeVo)session.getAttribute("employee");
        ArrayList<String> emails = new ArrayList<>();
        emails.add(employee.getEmail());
        validateCodeService.insertValidateCode(employee.getEmail(),code.toString());
        emailService.adEmail(emails,"验证码",code.toString(),null,null);
        hashMap.put("send",true);
        return hashMap;
    }



}

