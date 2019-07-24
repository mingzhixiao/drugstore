package com.wzw.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration    //实现mvc视图配置接口
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //指定默认的视图路径
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/main").setViewName("home");
        registry.addViewController("/index").setViewName("login");


    }

/*    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //先添加拦截的路径
        //放行路径
        registry.addInterceptor(new MyLoginInterceptors()).addPathPatterns("/**").excludePathPatterns("/","/index","/login");
    }*/


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
}