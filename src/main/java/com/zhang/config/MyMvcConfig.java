package com.zhang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        //登录模块视图

        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/unauthorized").setViewName("/error/unauthorized");

        //管理员模块视图
        registry.addViewController("/admin").setViewName("/admin/indexAdmin");

        //教师模块视图
        registry.addViewController("/teacher").setViewName("/teacher/indexTeacher");

        //学生模块视图
        registry.addViewController("/student").setViewName("/student/indexStudent");
    }
}
