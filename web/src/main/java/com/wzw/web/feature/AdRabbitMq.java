package com.wzw.web.feature;

import com.wzw.service.ICustomerService;
import com.wzw.service.feature.EmailService;
import com.wzw.utils.Page;
import com.wzw.vo.CustomerVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/*
 * @program: drugstore
 * @description: 广告推送
 * @author: wzw
 * @create: 2019-03-27 16:37
 */

@Controller
public class AdRabbitMq {

    @Resource
    ICustomerService customerService;

    @Resource
    EmailService adRabbitMqService;

    @RequestMapping("/adRabbitMq")
    public String adRabbitMq(String subject, String text, String zipName, String zipUrl, ModelMap modelMap){
        Page<CustomerVo> customerVoPage = customerService.allCustomerVo(1, 1000);
        List<CustomerVo> customerVoList = customerVoPage.getItems();
        ArrayList<String> emails = new ArrayList<>();
        for (CustomerVo customerVo: customerVoList
        ) {
            String email = customerVo.getEmail();
            emails.add(email);
        }
        Boolean result = adRabbitMqService.adEmail(emails, subject, text, zipName, zipUrl);
        String finalResult = "发送失败";
        if (result){
            finalResult ="发送成功";
        }
        modelMap.addAttribute("emailMessage",finalResult);
        return "/";
    }
}
