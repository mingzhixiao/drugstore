package com.wzw.service.feature;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: drugstore
 * @description: 广告推送业务
 * @author: wzw
 * @create: 2019-03-27 17:04
 */
public interface EmailService {
    Boolean adEmail(ArrayList<String> emails, String subject, String text,String zipName, String zipUrl);
}
