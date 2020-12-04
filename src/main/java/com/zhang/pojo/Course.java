package com.zhang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Integer courseID;
    private String courseName;
    private Integer teacherID;
    private Integer courseTime;
    private String classRoom;
    private Integer courseWeek;
    private Integer courseType;
    private Integer collegeID;
    private Integer score;
    private Teacher teacher;
    private College college;
}
