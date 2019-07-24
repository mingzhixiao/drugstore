/*
package com.wzw;

import com.wzw.service.ICustomerService;
import com.wzw.service.feature.EmailService;
import com.wzw.vo.CustomerVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProvideApplicationTests {
    @Resource
    EmailService adRabbitMqService;

    @Resource
    ICustomerService customerService;
    @Test
    public void contextLoads() {
        List<CustomerVo> customerVos = customerService.allCustomerVo();
        ArrayList<String> emails = new ArrayList<>();
        for (CustomerVo customerVo:customerVos
             ) {
            emails.add(customerVo.getEmail());
        }
        //adRabbitMqService.adEmail(emails);
    }

}
*/
