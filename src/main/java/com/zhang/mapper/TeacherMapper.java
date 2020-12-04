package com.zhang.mapper;

import com.zhang.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherMapper {
    //根据UserID查询老师信息
    Teacher queryTeacherById(Integer id);

    //根据teacherID查询课程信息
    List<Course> queryCourseById(Integer id);

    //查询所有学院信息
    List<College> queryAllCollege();

    //修改课程信息
    int updateCourse(Course course);

    //添加课程信息
    int addCourse(Course course);

    //通过CourseID查询课程信息
    Course queryCourseByCourseId(Integer id);

    //通过CourseID查询学生成绩
    List<SelectedCourse> queryMarkByCourseID(Integer id);

    //通过CourseID和studentID查询学生成绩
    SelectedCourse queryMarkID(Integer courseID, Integer studentID);

    //对学生进行打分
    int addMark(SelectedCourse selectedCourse);

    //根据teacherID查询User
    User queryUserById(Integer id);

    //教师修改个人密码
    int updateUser(User user);

}
