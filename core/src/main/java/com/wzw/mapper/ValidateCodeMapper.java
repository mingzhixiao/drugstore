package com.wzw.mapper;

import com.wzw.entity.ValidateCode;

public interface ValidateCodeMapper {
    int deleteValidateCode(String email);

    int insert(ValidateCode record);

    String getValidateCodeByEmail(String email);
}