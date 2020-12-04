package com.zhang.service;

import com.zhang.mapper.AdminMapper;
import com.zhang.pojo.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<College> queryAllCollege() {
        return adminMapper.queryAllCollege();
    }

    @Override
    public College queryCollegeById(Integer id) {
        return adminMapper.queryCollegeById(id);
    }

    @Override
    public List<Student> queryAllStudent() {
        return adminMapper.queryAllStudent();
    }

    @Override
    public Student queryStudentById(Integer id) {
        return adminMapper.queryStudentById(id);
    }

    @Override
    public int updateStudent(Student student) {
        return adminMapper.updateStudent(student);
    }

    @Override
    public int addStudent(Student student) {
        return adminMapper.addStudent(student);
    }

    @Override
    public int deleteStudent(Integer id) {
        return adminMapper.deleteStudent(id);
    }

    @Override
    public List<User> queryAllUser() {
        return adminMapper.queryAllUser();
    }

    @Override
    public User queryUserById(Integer id) {
        return adminMapper.queryUserById(id);
    }

    @Override
    public User queryUserByName(String name) {
        return adminMapper.queryUserByName(name);
    }

    @Override
    public int updateUser(User user) {
        if (user.getPassword().length() <= 15)
            user.setPassword(Shiro_md5(user));
        return adminMapper.updateUser(user);
    }

    @Override
    public int addUser(User user) {
        user.setPassword(Shiro_md5(user));
        return adminMapper.addUser(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return adminMapper.deleteUser(id);
    }

    @Override
    public List<Teacher> queryAllTeacher() {
        return adminMapper.queryAllTeacher();
    }

    @Override
    public Teacher queryTeacherById(Integer id) {
        return adminMapper.queryTeacherById(id);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return adminMapper.updateTeacher(teacher);
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return adminMapper.addTeacher(teacher);
    }

    @Override
    public int deleteTeacher(Integer id) {
        return adminMapper.deleteTeacher(id);
    }

    @Override
    public List<Course> queryAllCourse() {
        return adminMapper.queryAllCourse();
    }

    @Override
    public Course queryCourseById(Integer id) {
        return adminMapper.queryCourseById(id);
    }

    @Override
    public int updateCourse(Course course) {
        return adminMapper.updateCourse(course);
    }

    @Override
    public int addCourse(Course course) {
        return adminMapper.addCourse(course);
    }

    @Override
    public int deleteCourse(Integer id) {
        return adminMapper.deleteCourse(id);
    }

    @Override
    public List<Teacher> queryTeacherByCollege(Integer id) {
        return adminMapper.queryTeacherByCollege(id);
    }

    @Override
    public int querySelectedCourseById(Integer id) {
        return adminMapper.countSelectedCourseById(id);
    }

    public static String Shiro_md5(User user) {
        int hashIterations = 10000;//加密的次数
        Object salt = user.getUserName();//盐值
        Object credentials = user.getPassword();//密码
        String hashAlgorithmName = "MD5";//加密方式
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        //System.out.println("加密后的值----->" + simpleHash);
        return simpleHash.toString();
    }
}