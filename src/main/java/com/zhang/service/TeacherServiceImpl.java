package com.zhang.service;

import com.zhang.mapper.TeacherMapper;
import com.zhang.pojo.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public Teacher queryTeacherById(Integer id) {
        return teacherMapper.queryTeacherById(id);
    }

    @Override
    public List<Course> queryCourseById(Integer id) {
        return teacherMapper.queryCourseById(id);
    }

    @Override
    public List<College> queryAllCollege() {
        return teacherMapper.queryAllCollege();
    }

    @Override
    public int updateCourse(Course course) {
        return teacherMapper.updateCourse(course);
    }

    @Override
    public int addCourse(Course course) {
        return teacherMapper.addCourse(course);
    }

    @Override
    public Course queryCourseByCourseId(Integer id) {
        return teacherMapper.queryCourseByCourseId(id);
    }

    @Override
    public List<SelectedCourse> queryMarkByCourseID(Integer id) {
        return teacherMapper.queryMarkByCourseID(id);
    }

    @Override
    public SelectedCourse queryMarkID(Integer courseID, Integer studentID) {
        return teacherMapper.queryMarkID(courseID, studentID);
    }

    @Override
    public int addMark(SelectedCourse selectedCourse) {
        return teacherMapper.addMark(selectedCourse);
    }

    @Override
    public User queryUserById(Integer id) {
        return teacherMapper.queryUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return teacherMapper.updateUser(user);
    }

    @Override
    public boolean queryCourseIsTrue(Integer id) {
        boolean flag = false;

        Teacher teacherById = queryTeacherById(Integer.valueOf(getSession().getUserName()));
        List<Course> courses = queryCourseById(teacherById.getUserID());

        for (Course course : courses) {
            if (course.getCourseID().equals(id)) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    @Override
    public User Shiro_md5(User user) {
        int hashIterations = 10000;//加密的次数
        Object salt = user.getUserName();//盐值
        Object credentials = user.getPassword();//密码
        String hashAlgorithmName = "MD5";//加密方式
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        //System.out.println("加密后的值----->" + simpleHash);
        user.setPassword(simpleHash.toString());
        return user;
    }

    @Override
    public User getSession() {
        Subject subject = SecurityUtils.getSubject();
        return (User) subject.getPrincipal();
    }
}
