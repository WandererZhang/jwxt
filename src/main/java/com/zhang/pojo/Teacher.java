package com.zhang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private Integer userID;
    private String userName;
    private Integer sex;
    private Date birthYear;
    private Integer degree;
    private Integer title;
    private Integer collegeID;
    private College college;
}
