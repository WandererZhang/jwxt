package com.zhang.service;

import com.zhang.mapper.StudentMapper;
import com.zhang.pojo.Course;
import com.zhang.pojo.SelectedCourse;
import com.zhang.pojo.Student;
import com.zhang.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student queryStudentById(Integer id) {
        return studentMapper.queryStudentById(id);
    }

    @Override
    public List<Course> queryNoCourseById(Integer collegeID, Integer studentID) {
        return studentMapper.queryNoCourseById(collegeID,studentID);
    }

    @Override
    public List<Course> querySelectCourseById(Integer id) {
        return studentMapper.querySelectCourseById(id);
    }

    @Override
    public List<SelectedCourse> queryFinishCourseById(Integer id) {
        return studentMapper.queryFinishCourseById(id);
    }

    @Override
    public int addCourse(SelectedCourse selectedCourse) {
        return studentMapper.addCourse(selectedCourse);
    }

    @Override
    public int deleteCourse(SelectedCourse selectedCourse) {
        return studentMapper.deleteCourse(selectedCourse);
    }

    @Override
    public int queryIsSuccess(SelectedCourse selectedCourse) {
        return studentMapper.queryIsSuccess(selectedCourse);
    }

    @Override
    public int queryIsFinish(SelectedCourse selectedCourse) {
        return studentMapper.queryIsFinish(selectedCourse);
    }

    @Override
    public User queryUserById(Integer id) {
        return studentMapper.queryUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return studentMapper.updateUser(user);
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
        User user = (User) subject.getPrincipal();
        return user;
    }
}
