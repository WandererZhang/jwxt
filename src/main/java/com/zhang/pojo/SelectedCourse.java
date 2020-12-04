package com.zhang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedCourse {
    private Integer courseID;
    private Integer studentID;
    private Integer mark;
    private Teacher teacher;
    private Course course;
    private Student student;
}
