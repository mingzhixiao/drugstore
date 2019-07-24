package com.wzw.service.feature;

import com.wzw.vo.ValidateCodeVo;

/**
 * @program: drugstore
 * @description: 验证码操作
 * @author: wzw
 * @create: 2019-03-28 11:33
 */
public interface IValidateCodeService {
    Boolean insertValidateCode(String email,String code);
    Boolean deleteValidateCode(String  email);
    String getPasswordByEmail(String email);
}
