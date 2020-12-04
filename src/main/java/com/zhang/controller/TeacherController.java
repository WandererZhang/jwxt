package com.zhang.controller;

import com.zhang.pojo.Course;
import com.zhang.pojo.SelectedCourse;
import com.zhang.pojo.Teacher;
import com.zhang.pojo.User;
import com.zhang.service.TeacherService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @RequestMapping("/teacher")
    public String queryTeacherById(Model model) {
        //显示教师信息
        Teacher teacher = teacherService.queryTeacherById(Integer.valueOf(teacherService.getSession().getUserName()));
        model.addAttribute("teacher", teacher);
        return "/teacher/indexTeacher";
    }

    @RequestMapping("/teacher/showCourse")
    public String teacherListCourse(Model model) {
        //列表显示所有课程
        Collection<Course> courses = teacherService.queryCourseById(Integer.valueOf(teacherService.getSession().getUserName()));
        Teacher teacher = teacherService.queryTeacherById(Integer.valueOf(teacherService.getSession().getUserName()));
        model.addAttribute("teacher", teacher);
        model.addAttribute("courses", courses);
        return "/teacher/showCourse";
    }

    @RequestMapping("/teacher/addCourse")
    public String showTeacherAddCourse(Model model) {
        //添加课程页面显示
        Teacher teacher = teacherService.queryTeacherById(Integer.valueOf(teacherService.getSession().getUserName()));
        model.addAttribute("teacher", teacher);
        return "/teacher/addCourse";
    }

    @PostMapping("/teacher/addCourse")
    public String teacherAddCourse(Course course, Model model) {
        //添加课程信息
        if (teacherService.addCourse(course) > 0)
            model.addAttribute("msg", "添加成功");
        return "forward:/teacher/showCourse";
    }

    @RequestMapping("/teacher/updateCourse")
    public String showTeacherUpdateCourse(Model model, HttpServletRequest request) {
        //更新课程信息页面显示
        Integer id = Integer.valueOf(request.getParameter("id"));
        Teacher teacherById = teacherService.queryTeacherById(Integer.valueOf(teacherService.getSession().getUserName()));
        Course course = teacherService.queryCourseByCourseId(id);
        if (!teacherById.getUserID().equals(course.getTeacherID())) {
            model.addAttribute("msg", "没有权限!");
            return "forward:/teacher/showCourse";
        }
        model.addAttribute("teacher", teacherById);
        model.addAttribute("course", course);
        return "/teacher/updateCourse";
    }

    @PostMapping("/teacher/updateCourse")
    public String teacherUpdateCourse(Course course, Model model) {
        //更新课程信息
        if (teacherService.updateCourse(course) > 0)
            model.addAttribute("msg", "修改成功");
        return "forward:/teacher/showCourse";
    }

    @RequestMapping("/teacher/showMark")
    public String teacherShowMark(Model model, HttpServletRequest request) {
        //显示学生课程成绩
        Integer id = Integer.valueOf(request.getParameter("id"));
        List<SelectedCourse> selectedCourses = teacherService.queryMarkByCourseID(id);

        if (selectedCourses.size() == 0) {
            model.addAttribute("msg", "暂时没有学生选择该门课程!");
            return "forward:/teacher/showCourse?id=" + id.toString();
        } else if (!teacherService.queryCourseIsTrue(id)) {
            model.addAttribute("msg", "没有权限!");
            return "forward:/teacher/showCourse?id=" + id.toString();
        } else {
            model.addAttribute("selectedCourses", selectedCourses);
            return "/teacher/showMark";
        }
    }

    @GetMapping("/teacher/addMark")
    public String showTeacherAddMark(@RequestParam("courseID") Integer courseID, @RequestParam("studentID") Integer studentID, Model model) {
        //对学生打分界面显示
        SelectedCourse selectedCourse = teacherService.queryMarkID(courseID, studentID);
        if (selectedCourse.getMark() != null) {
            model.addAttribute("msg", "学生成绩已经存在!");
            return "forward:/teacher/showMark?id=" + selectedCourse.getCourseID().toString();
        } else {
            model.addAttribute("selectedCourse", selectedCourse);
            return "/teacher/addMark";
        }
    }

    @PostMapping("/teacher/addMark")
    public String teacherAddMark(Model model, SelectedCourse selectedCourse) {
        //对学生进行学生打分
        if (teacherService.addMark(selectedCourse) > 0)
            model.addAttribute("msg", "打分成功");
        return "forward:/teacher/showMark?id=" + selectedCourse.getCourseID().toString();
    }

    @RequestMapping("/teacher/updateUser")
    public String teacherShowUpdateUser(Model model) {
        //更新用户信息页面显示
        User userById = teacherService.queryUserById(Integer.valueOf(teacherService.getSession().getUserName()));
        model.addAttribute("user", userById);
        return "/teacher/updateUser";
    }

    @PostMapping("/teacher/updateUser")
    public String teacherUpdateUser(User user, Model model) {
        //修改用户信息
        User lastUser = teacherService.getSession();
        User curUser = teacherService.Shiro_md5(user);
        if (curUser.getPassword().equals(lastUser.getPassword())) {
            model.addAttribute("msg", "不能与原密码相同!");
            return "/teacher/updateUser";
        } else if (teacherService.updateUser(curUser) > 0) {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            model.addAttribute("msg", "修改成功,请重新登录!");
            return "/login";
        }
        return "/teacher/updateUser";
    }
}
