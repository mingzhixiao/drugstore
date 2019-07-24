package com.wzw.config;

import com.wzw.service.IEmployeeService;
import com.wzw.vo.EmployeeVo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @Description TODO
 * @Date 2019/4/26 16:29
 * @Created by wzw
 */
public class MyUserRealm extends AuthorizingRealm {
    @Resource
    IEmployeeService employeeService;


    /**
     * @description 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }




    /**
     * @description 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        ArrayList<EmployeeVo> employeeVos = employeeService.getEmployeeByName(token.getUsername());
        if (employeeVos.size() == 0){
            throw new UnknownAccountException("用户不存在");
        }




        return new SimpleAuthenticationInfo(employeeVos.get(0).getName(),employeeVos.get(0).getPassword(),getName());

    }
}
