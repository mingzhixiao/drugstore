package com.wzw.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyLoginInterceptors implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            request.setAttribute("msg", "没有权限，请先登录");
            request.getRequestDispatcher("/goLogin").forward(request, response);
            return false;
        }
        return  true;
    }
}