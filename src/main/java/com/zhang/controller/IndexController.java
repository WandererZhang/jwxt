package com.zhang.controller;

import com.zhang.pojo.User;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String setIndex(){
        return "redirect:/index";
    }
    //判断用户的权限并跳转到相应的主页
    @RequestMapping("/index")
    public String JudgeRole(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        //将user放入session中
        Session session = subject.getSession();
        session.setAttribute("loginUser",user);

        if (user.getRole_db().getRoleName().equals("admin")) {
            return "redirect:/admin";
        } else if (user.getRole_db().getRoleName().equals("teacher")) {
            return "redirect:/teacher";
        } else if (user.getRole_db().getRoleName().equals("student")) {
            return "redirect:/student";
        }
        return "redirect:/login";
    }
}
