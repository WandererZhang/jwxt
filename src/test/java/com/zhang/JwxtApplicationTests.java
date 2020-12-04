package com.zhang;

import com.zhang.mapper.AdminMapper;
import com.zhang.mapper.StudentMapper;
import com.zhang.mapper.TeacherMapper;
import com.zhang.pojo.College;
import com.zhang.pojo.SelectedCourse;
import com.zhang.pojo.Student;
import com.zhang.pojo.Teacher;
import com.zhang.service.UserServiceImpl;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class JwxtApplicationTests {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    TeacherMapper teacherMapper;

    @Test
    public void getStudent() {
        List<Student> students = adminMapper.queryAllStudent();
        for (Student student : students) {
            System.out.println(student);
        }
    }
    @Test
    public void getUserService(){
            System.out.println(userService.queryUserByName("admin"));
    }
    @Test
    public void Shiro_md5() {
        int hashIterations = 10000;//加密的次数
        Object salt = "1006";//盐值
        Object credentials = "123456";//密码
        String hashAlgorithmName = "MD5";//加密方式
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println("加密后的值----->" + simpleHash);
    }
    @Test
    public void getStudentById(){
        Student student = adminMapper.queryStudentById(10001);
        System.out.println(student);
    }
    @Test
    public void getCollege(){
        List<College> colleges = adminMapper.queryAllCollege();
        College college = adminMapper.queryCollegeById(2);
        for (College college1 : colleges) {
            System.out.println(college1);
        }
        System.out.println();
        System.out.println(college);
    }
    @Test
    public void getCourse(){
        adminMapper.queryAllCourse();
    }
    @Test
    public void countSelectedCourseById(){
        System.out.println(adminMapper.countSelectedCourseById(1));
    }
    @Test
    public void queryTeacherById(){
        Teacher teacher = teacherMapper.queryTeacherById(1001);
    }
    @Test
    public void queryMarkByCourseID(){
        System.out.println(teacherMapper.queryMarkByCourseID(1));
    }
    @Test
    public void queryMarkById(){
        System.out.println(teacherMapper.queryMarkID(2,10001).getMark());
    }
    @Test
    public void queryStudentById(){
        System.out.println(studentMapper.queryStudentById(10001));
    }
    @Test
    public void querySelectById(){
        System.out.println(studentMapper.queryNoCourseById(1,10001));
    }
    @Test
    public void addCourse(){
        SelectedCourse course = new SelectedCourse();
        course.setStudentID(19999);
        course.setCourseID(1);
        course.setMark(88);
        studentMapper.addCourse(course);
    }
    @Test
    public void queryFinishCourseById(){
        System.out.println(studentMapper.queryFinishCourseById(10001));
    }
}
