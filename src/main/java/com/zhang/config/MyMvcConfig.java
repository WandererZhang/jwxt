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
//        registry.addViewController("/admin/showStudent").setViewName("/admin/showStudent");
//        registry.addViewController("/admin/addStudent").setViewName("/admin/addStudent");
//        registry.addViewController("/admin/updateStudent").setViewName("/admin/updateStudent");
//        registry.addViewController("/admin/showUser").setViewName("/admin/showUser");
//        registry.addViewController("/admin/addUser").setViewName("/admin/addUser");
//        registry.addViewController("/admin/updateUser").setViewName("/admin/updateUser");
//        registry.addViewController("/admin/showTeacher").setViewName("/admin/showTeacher");
//        registry.addViewController("/admin/addTeacher").setViewName("/admin/addTeacher");
//        registry.addViewController("/admin/updateTeacher").setViewName("/admin/updateTeacher");
//        registry.addViewController("/admin/showCourse").setViewName("/admin/showCourse");
//        registry.addViewController("/admin/addCourse").setViewName("/admin/addCourse");
//        registry.addViewController("/admin/updateCourse").setViewName("/admin/updateCourse");

        //教师模块视图
        registry.addViewController("/teacher").setViewName("/teacher/indexTeacher");
//        registry.addViewController("/teacher/showCourse").setViewName("/teacher/showCourse");
//        registry.addViewController("/teacher/addCourse").setViewName("/teacher/addCourse");
//        registry.addViewController("/teacher/updateCourse").setViewName("/teacher/updateCourse");

        //学生模块视图
        registry.addViewController("/student").setViewName("/student/indexStudent");
    }
}
