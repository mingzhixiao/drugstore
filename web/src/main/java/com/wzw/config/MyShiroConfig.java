
package com.wzw.config;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;




/**
 * @Description TODO
 * @Date 2019/4/26 16:26
 * @Created by wzw
 */



@Configuration
public class MyShiroConfig {



/**
 * @description 创建shiroFilterFactoryBean
 */



    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        //添加shiro内置过滤器

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
       //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        Map<String, String> filterMap = new LinkedHashMap<>();


        filterMap.put("/static/**","anon");
        filterMap.put("/webjars/**","anon");

        filterMap.put("/login/login","anon");

        filterMap.put("/doc.html","anon");

        filterMap.put("/logout","logout");

        filterMap.put("/**","authc");






        //修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/login/goLogin");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }



    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);// 散列的次数，比如散列两次，相当于md5(md5(""));
        return hashedCredentialsMatcher;
    }





/**
 * @description 创建DefaultWebSecurityManager
 */



    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myUserRealm") MyUserRealm myUserRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myUserRealm);
        return securityManager;
    }




/**
 * @description 创建Realm
 */



    @Bean(name = "myUserRealm")
    public MyUserRealm getMyUserRealm(){
        MyUserRealm myUserRealm = new MyUserRealm();
        return myUserRealm;
    }



}



