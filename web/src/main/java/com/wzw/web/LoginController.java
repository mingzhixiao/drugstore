package com.wzw.web;

import com.wzw.service.IEmployeeService;
import com.wzw.vo.EmployeeVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Description TODO
 * @Date 2019/4/8 17:20
 * @Created by wzw
 */
@RestController()
@RequestMapping("/login")
public class LoginController {
    @Resource
    IEmployeeService employeeService;

    @RequestMapping("/goLogin")
    public ModelAndView goLogin(ModelMap modelMap){
        return new ModelAndView("login");
    }

    @RequestMapping("/goHome")
    public ModelAndView goHome(){
        return new ModelAndView("home");
    }




    @PostMapping("/login")
    public ModelAndView login(String username, String password, int typeId, ModelMap modelMap, HttpSession session)  {

        ArrayList<EmployeeVo> employeeByName = employeeService.getEmployeeByName(username);
        if (employeeByName.size() < 0){
            modelMap.addAttribute("msg","用户名或密码不正确");
            return new ModelAndView("login");
        }
        for (EmployeeVo employee : employeeByName)
        {
            if (typeId == employee.getSet()){
                Subject subject = SecurityUtils.getSubject();

                UsernamePasswordToken token = new UsernamePasswordToken(username, password);

                try {
                    subject.login(token);
                    session.setAttribute("employee",employee);
                    return new ModelAndView("redirect:/login/goHome");
                } catch (UnknownAccountException e){
                    modelMap.addAttribute("msg","用户名不正确");
                    return new ModelAndView("login");
                } catch (IncorrectCredentialsException e){
                    modelMap.addAttribute("msg","密码不正确");
                    return new ModelAndView("login");
                }
            }
        }


        modelMap.addAttribute("msg","用户名或密码不正确");
        return new ModelAndView("login");


    }
}
