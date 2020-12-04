package com.zhang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer userID;
    private String userName;
    private Integer studentNumber;
    private Integer sex;
    private Integer grade;
    private Date birthYear;
    private Integer collegeID;
    private College college;
}
