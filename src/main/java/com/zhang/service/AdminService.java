package com.zhang.service;

import com.zhang.pojo.*;

import java.util.List;

public interface AdminService {
    //查询所有学院信息
    List<College> queryAllCollege();

    //通过CollegeID查询学院信息
    College queryCollegeById(Integer id);

    //查询所有学生信息
    List<Student> queryAllStudent();

    //通过userID查询学生信息
    Student queryStudentById(Integer id);

    //修改学生信息
    int updateStudent(Student student);

    //添加学生信息
    int addStudent(Student student);

    //删除学生信息
    int deleteStudent(Integer id);

    //查询所有用户信息
    List<User> queryAllUser();

    //通过userID查询用户信息
    User queryUserById(Integer id);

    //通过userName查询用户信息
    User queryUserByName(String name);

    //修改用户信息
    int updateUser(User user);

    //添加用户信息
    int addUser(User user);

    //删除用户信息
    int deleteUser(Integer id);

    //查询所有老师信息
    List<Teacher> queryAllTeacher();

    //通过userID查询老师信息
    Teacher queryTeacherById(Integer id);

    //修改老师信息
    int updateTeacher(Teacher teacher);

    //添加老师信息
    int addTeacher(Teacher teacher);

    //删除老师信息
    int deleteTeacher(Integer id);

    //查询所有课程信息
    List<Course> queryAllCourse();

    //通过userID查询课程信息
    Course queryCourseById(Integer id);

    //修改课程信息
    int updateCourse(Course course);

    //添加课程信息
    int addCourse(Course course);

    //删除课程信息
    int deleteCourse(Integer id);

    //根据学院查询老师信息
    List<Teacher> queryTeacherByCollege(Integer id);

    //查询是否有学生选中课程
    int querySelectedCourseById(Integer id);
}
