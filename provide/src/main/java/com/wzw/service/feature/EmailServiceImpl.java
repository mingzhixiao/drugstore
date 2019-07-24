package com.wzw.service.feature;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;

/**
 * @program: drugstore
 * @description: 广告推送业务
 * @author: wzw
 * @create: 2019-03-27 17:06
 */
@Service("adRabbitMqService")
public class EmailServiceImpl implements EmailService {
    @Autowired
    JavaMailSenderImpl javaMailSender;
    @Override
    public Boolean adEmail(ArrayList<String> emails ,String subject, String text,String zipName,String zipUrl) {

        Boolean result  = false;
/*        int i = 0;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("大家好");
        message.setText("hello");

        System.out.println("邮箱个数："+emails.size());
        message.setTo(emailString);
        message.setFrom("1519900797@qq.com");
        javaMailSender.send(message);*/

        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;//true代表开启html格式
        try {
            helper = new MimeMessageHelper(mimeMessage,true);
            //邮件设置
            helper.setSubject(subject);
            helper.setText(text,true);
            String[] emailString =new String[emails.size()];
            int i = 0;
            for (String email : emails) {
                emailString[i++] = email;
                // System.out.println(" 内容 "+email);
            }
            helper.setFrom("1519900797@qq.com");




            helper.setTo(emailString);

//上传文件
            if (zipUrl != null){
                helper.addAttachment(zipName,new File(zipUrl));
            }
            result = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
         javaMailSender.send(mimeMessage);
        return result;
    }

}
