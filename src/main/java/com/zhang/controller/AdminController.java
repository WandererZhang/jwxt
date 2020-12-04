package com.zhang.controller;

import com.zhang.pojo.*;
import com.zhang.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/admin/showStudent")
    public String listStudent(Model model) {
        //列表显示所有学生
        Collection<Student> students = adminService.queryAllStudent();
        model.addAttribute("students", students);
        return "/admin/showStudent";
    }

    @GetMapping("/admin/updateStudent/{id}")
    public String showUpdateStudent(@PathVariable("id") Integer id, Model model) {
        //更新学生信息页面显示
        Student studentById = adminService.queryStudentById(id);
        List<College> collegeList = adminService.queryAllCollege();
        model.addAttribute("student", studentById);
        model.addAttribute("colleges", collegeList);
        return "/admin/updateStudent";
    }

    @PostMapping("/admin/updateStudent")
    public String updateStudent(Student student, Model model) {
        //修改学生信息
        if (adminService.updateStudent(student) > 0)
            model.addAttribute("msg", "修改成功");
        return "forward:/admin/showStudent";
    }

    @RequestMapping("/admin/addStudent")
    public String showAddStudent(Model model) {
        //添加学生界面读取院系信息
        List<College> collegeList = adminService.queryAllCollege();
        model.addAttribute("colleges", collegeList);
        return "/admin/addStudent";
    }

    @PostMapping("/admin/addStudent")
    public String addStudent(Student student, Model model) {
        //添加学生信息
        if (adminService.addStudent(student) > 0)
            model.addAttribute("msg", "添加成功");
        return "forward:/admin/showStudent";
    }

    @GetMapping("/admin/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") Integer id, Model model) {
        //删除学生信息
        if (adminService.deleteStudent(id) > 0)
            model.addAttribute("msg", "删除成功");
        return "forward:/admin/showStudent";
    }

    @RequestMapping("/admin/showUser")
    public String listUser(Model model) {
        //列表显示所有用户
        List<User> users = adminService.queryAllUser();
        model.addAttribute("users", users);
        return "/admin/showUser";
    }

    @GetMapping("/admin/updateUser/{id}")
    public String showUpdateUser(@PathVariable("id") Integer id, Model model) {
        //更新用户信息页面显示
        User userById = adminService.queryUserById(id);
        model.addAttribute("user", userById);
        return "/admin/updateUser";
    }

    @PostMapping("/admin/updateUser")
    public String updateUser(User user, Model model) {
        //修改用户信息
        if (adminService.updateUser(user) > 0)
            model.addAttribute("msg", "修改成功");
        return "forward:/admin/showUser";
    }

    @RequestMapping("/admin/addUser")
    public String showAddUser() {
        //跳转到addUser界面
        return "/admin/addUser";
    }

    @PostMapping("/admin/addUser")
    public String addUser(User user, Model model) {
        //添加用户信息
        if (adminService.queryUserByName(user.getUserName()) == null) {
            if (adminService.addUser(user) > 0) {
                model.addAttribute("msg", "添加成功");
            }
        } else {
            model.addAttribute("msg", "用户名已存在");
        }
        return "forward:/admin/showUser";
    }

    @GetMapping("/admin/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        //删除用户信息
        if (adminService.deleteUser(id) > 0)
            model.addAttribute("msg", "删除成功");
        return "forward:/admin/showUser";
    }

    @RequestMapping("/admin/showTeacher")
    public String listTeacher(Model model) {
        //列表显示所有老师
        Collection<Teacher> teachers = adminService.queryAllTeacher();
        model.addAttribute("teachers", teachers);
        return "/admin/showTeacher";
    }

    @GetMapping("/admin/updateTeacher/{id}")
    public String showUpdateTeacher(@PathVariable("id") Integer id, Model model) {
        //更新老师信息页面显示
        Teacher teacherById = adminService.queryTeacherById(id);
        List<College> collegeList = adminService.queryAllCollege();
        model.addAttribute("teacher", teacherById);
        model.addAttribute("colleges", collegeList);
        return "/admin/updateTeacher";
    }

    @PostMapping("/admin/updateTeacher")
    public String updateTeacher(Teacher teacher, Model model) {
        //修改老师信息
        if (adminService.updateTeacher(teacher) > 0)
            model.addAttribute("msg", "修改成功");
        return "forward:/admin/showTeacher";
    }

    @RequestMapping("/admin/addTeacher")
    public String showAddTeacher(Model model) {
        //添加老师界面读取院系信息
        List<College> collegeList = adminService.queryAllCollege();
        model.addAttribute("colleges", collegeList);
        return "/admin/addTeacher";
    }

    @PostMapping("/admin/addTeacher")
    public String addTeacher(Teacher teacher, Model model) {
        //添加老师信息
        if (adminService.addTeacher(teacher) > 0)
            model.addAttribute("msg", "添加成功");
        return "forward:/admin/showTeacher";
    }

    @GetMapping("/admin/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable("id") Integer id, Model model) {
        //删除老师信息
        if (adminService.deleteTeacher(id) > 0)
            model.addAttribute("msg", "删除成功");
        return "forward:/admin/showTeacher";
    }

    @RequestMapping("/admin/showCourse")
    public String listCourse(Model model) {
        //列表显示所有课程
        Collection<Course> courses = adminService.queryAllCourse();
        model.addAttribute("courses", courses);
        return "/admin/showCourse";
    }

    @RequestMapping("/admin/addCourse")
    public String showAddCourse(Model model) {
        //读取院系信息到添加课程界面
        List<College> colleges = adminService.queryAllCollege();
        model.addAttribute("colleges", colleges);
        return "/admin/addCourse";
    }

    @RequestMapping("/admin/addCourse/query")
    @ResponseBody
    public Map<String, Object> addTeacherAJAX(Integer collegeID) {
        //响应根据学院查找老师的AJAX
        List<Teacher> teachers = adminService.queryTeacherByCollege(collegeID);
        Map<String, Object> map = new HashMap<>();
        map.put("teachers", teachers);
        return map;
    }

    @PostMapping("/admin/addCourse")
    public String addCourse(Course course, Model model) {
        //添加课程信息
        if (adminService.addCourse(course) > 0)
            model.addAttribute("msg", "添加成功");
        return "forward:/admin/showCourse";
    }

    @GetMapping("/admin/deleteCourse/{id}")
    public String deleteCourse(@PathVariable("id") Integer id, Model model) {
        //删除课程信息
        if (adminService.querySelectedCourseById(id) > 0) {
            System.out.println(adminService.querySelectedCourseById(id));
            model.addAttribute("msg", "删除失败,已经有学生选择了这门课");
            return "forward:/admin/showCourse";
        } else if (adminService.deleteCourse(id) > 0) {
            model.addAttribute("msg", "删除成功");
        }
        return "forward:/admin/showCourse";
    }

    @GetMapping("/admin/updateCourse/{id}")
    public String showUpdateCourse(@PathVariable("id") Integer id, Model model) {
        //更新课程信息页面显示
        Course courseById = adminService.queryCourseById(id);
        Teacher teacher =adminService.queryTeacherById(courseById.getTeacherID());
        List<College> collegeList = adminService.queryAllCollege();
        model.addAttribute("teacher",teacher);
        model.addAttribute("course", courseById);
        model.addAttribute("colleges", collegeList);
        return "/admin/updateCourse";
    }

    @RequestMapping(value = "/admin/updateCourse/query",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> updateTeacherAJAX(Integer collegeID) {
        //响应根据学院查找老师的AJAX
        List<Teacher> teachers = adminService.queryTeacherByCollege(collegeID);
        Map<String, Object> map = new HashMap<>();
        map.put("teachers", teachers);
        return map;
    }

    @PostMapping("/admin/updateCourse")
    public String updateCourse(Course course, Model model) {
        //修改课程信息
        if (adminService.updateCourse(course) > 0)
            model.addAttribute("msg", "修改成功");
        return "forward:/admin/showCourse";
    }
}
