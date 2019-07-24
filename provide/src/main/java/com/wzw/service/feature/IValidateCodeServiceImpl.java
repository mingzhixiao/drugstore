package com.wzw.service.feature;

import com.alibaba.fastjson.JSONObject;
import com.wzw.entity.ValidateCode;
import com.wzw.mapper.ValidateCodeMapper;
import com.wzw.service.utils.MyRunnableThread;
import com.wzw.vo.ValidateCodeVo;
import org.springframework.scheduling.annotation.Async;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Random;

/**
 * @program: drugstore
 * @description: 验证码操作
 * @author: wzw
 * @create: 2019-03-28 11:37
 */
@Service("validateCodeService")
public class IValidateCodeServiceImpl implements IValidateCodeService {
    @Resource
    ValidateCodeMapper validateCodeMapper;

    @Async
    @Override
    public Boolean insertValidateCode(String email,String code) {


        ValidateCode validateCode = new ValidateCode();
        validateCode.setEmail(email);
        validateCode.setPassword(code);
        validateCodeMapper.deleteValidateCode(email);
        int i = validateCodeMapper.insert(validateCode);


        MyRunnableThread myRunnableThread = new MyRunnableThread();
        Thread thread =new Thread(myRunnableThread);
        try {
            thread.sleep(300000);//设置五分钟的有效时间
            validateCodeMapper.deleteValidateCode(email);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (i > 0){
            return  true;
        }
        return false;
    }

    @Override
    public Boolean deleteValidateCode(String email) {
        int i = validateCodeMapper.deleteValidateCode(email);
        if (i > 0){
            return true;
        }
        return false;
    }

    @Override
    public String getPasswordByEmail(String email) {
        return validateCodeMapper.getValidateCodeByEmail(email);
    }
}
