package com.zhang.controller;


import com.zhang.pojo.Course;
import com.zhang.pojo.SelectedCourse;
import com.zhang.pojo.Student;
import com.zhang.pojo.User;
import com.zhang.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;


@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/student")
    public String queryStudentById(Model model, HttpServletRequest request) {
        //显示学生信息
        Integer studentID = Integer.valueOf(studentService.getSession().getUserName());
        Student student = studentService.queryStudentById(studentID);
        request.getSession().setAttribute("collegeID", student.getCollegeID());
        model.addAttribute("student", student);
        return "/student/indexStudent";
    }

    @RequestMapping("/student/showCourse")
    public String studentListCourse(Model model, HttpServletRequest request) {
        //列表显示可选课程
        Integer studentID = Integer.valueOf(studentService.getSession().getUserName());
        Integer collegeID = Integer.valueOf(request.getSession().getAttribute("collegeID").toString());
        Collection<Course> courses = studentService.queryNoCourseById(collegeID, studentID);
        model.addAttribute("courses", courses);
        return "/student/showCourse";
    }

    @GetMapping("/student/addCourse")
    public String studentAddCourse(@RequestParam("courseID") Integer courseID, @RequestParam("studentID") Integer studentID, Model model) {
        //学生选课
        SelectedCourse course = new SelectedCourse();
        course.setStudentID(studentID);
        course.setCourseID(courseID);
        if (studentService.queryIsSuccess(course) != 0) {
            return "redirect:/student/showCourse";
        } else {
            model.addAttribute("msg", "选课成功");
            studentService.addCourse(course);
            return "forward:/student/showCourse";
        }
    }

    @RequestMapping("/student/showSelectCourse")
    public String studentListSelectCourse(Model model) {
        //列表显示已选课程
        Integer studentID = Integer.valueOf(studentService.getSession().getUserName());
        Collection<Course> courses = studentService.querySelectCourseById(studentID);
        model.addAttribute("courses", courses);
        return "/student/showSelectCourse";
    }

    @GetMapping("/student/deleteCourse")
    public String studentDeleteCourse(@RequestParam("courseID") Integer courseID, @RequestParam("studentID") Integer studentID, Model model) {
        //学生退课
        SelectedCourse course = new SelectedCourse();
        course.setStudentID(studentID);
        course.setCourseID(courseID);
        if (studentService.queryIsFinish(course) != 0) {
            return "redirect:/student/showSelectCourse";
        } else {
            model.addAttribute("msg", "退课成功");
            studentService.deleteCourse(course);
            return "forward:/student/showSelectCourse";
        }
    }

    @RequestMapping("/student/showFinishCourse")
    public String studentListFinishCourse(Model model) {
        //列表显示已修课程
        Integer studentID = Integer.valueOf(studentService.getSession().getUserName());
        Collection<SelectedCourse> courses = studentService.queryFinishCourseById(studentID);
        model.addAttribute("courses", courses);
        return "/student/showFinishCourse";
    }

    @RequestMapping("/student/updateUser")
    public String studentShowUpdateUser(Model model) {
        //更新用户信息页面显示
        User userById = studentService.queryUserById(Integer.valueOf(studentService.getSession().getUserName()));
        model.addAttribute("user", userById);
        return "/student/updateUser";
    }

    @PostMapping("/student/updateUser")
    public String studentUpdateUser(User user, Model model) {
        //修改用户信息
        User lastUser = studentService.getSession();
        User curUser = studentService.Shiro_md5(user);
        if (curUser.getPassword().equals(lastUser.getPassword())) {
            model.addAttribute("msg", "不能与原密码相同!");
            return "/student/updateUser";
        } else if (studentService.updateUser(curUser) > 0) {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            model.addAttribute("msg", "修改成功,请重新登录!");
            return "/login";
        }
        return "/student/updateUser";
    }
}
