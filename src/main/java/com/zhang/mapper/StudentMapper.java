package com.zhang.mapper;

import com.zhang.pojo.Course;
import com.zhang.pojo.SelectedCourse;
import com.zhang.pojo.Student;
import com.zhang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    //根据UserID查询学生信息
    Student queryStudentById(Integer id);

    //根据collegeID和studentID查询可选课程信息
    List<Course> queryNoCourseById(Integer collegeID,Integer studentID);

    //根据studentID查询已选课程信息
    List<Course> querySelectCourseById(Integer id);

    //根据studentID查询已修课程信息
    List<SelectedCourse> queryFinishCourseById(Integer id);

    //学生选课
    int addCourse(SelectedCourse selectedCourse);

    //学生退课
    int deleteCourse(SelectedCourse selectedCourse);

    //查询选课是否成功
    int queryIsSuccess(SelectedCourse selectedCourse);

    //查询是否已经有成绩
    int queryIsFinish(SelectedCourse selectedCourse);

    //根据studentID查询User
    User queryUserById(Integer id);

    //学生修改个人密码
    int updateUser(User user);
}
