/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : jwxt

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 05/12/2020 03:52:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `collegeID` int(11) NOT NULL AUTO_INCREMENT,
  `collegeName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名',
  PRIMARY KEY (`collegeID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (1, '计算机系');
INSERT INTO `college` VALUES (2, '设计系');
INSERT INTO `college` VALUES (3, '财经系');
INSERT INTO `college` VALUES (4, '日语系');
INSERT INTO `college` VALUES (5, '物理系');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `courseID` int(11) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名称',
  `teacherID` int(11) NOT NULL,
  `courseTime` int(2) NULL DEFAULT NULL COMMENT '开课时间(周)',
  `classRoom` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开课地点',
  `courseWeek` int(200) NULL DEFAULT NULL COMMENT '学时',
  `courseType` int(2) NULL DEFAULT NULL COMMENT '课程类型(1-必修,0-选修)',
  `collegeID` int(11) NOT NULL COMMENT '所属院系',
  `score` int(11) NOT NULL COMMENT '学分',
  PRIMARY KEY (`courseID`) USING BTREE,
  INDEX `teacherID`(`teacherID`) USING BTREE,
  INDEX `college_ibfk_2`(`collegeID`) USING BTREE,
  CONSTRAINT `college_ibfk_2` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'C语言程序设计', 1006, 1, '科401', 18, 1, 1, 3);
INSERT INTO `course` VALUES (2, 'Python爬虫技巧', 1006, 2, 'X402', 18, 0, 1, 3);
INSERT INTO `course` VALUES (3, '数据结构', 1006, 3, '科401', 18, 1, 1, 2);
INSERT INTO `course` VALUES (4, 'Java程序设计', 1006, 4, '科401', 18, 1, 1, 2);
INSERT INTO `course` VALUES (5, '英语', 1008, 5, 'X302', 18, 0, 2, 2);
INSERT INTO `course` VALUES (6, '服装设计', 1008, 2, '科401', 18, 1, 2, 2);
INSERT INTO `course` VALUES (7, '日语基础', 1007, 4, 'X201', 18, 1, 4, 2);
INSERT INTO `course` VALUES (20, '234234', 1001, 1, '23423', 20, 1, 2, 15);
INSERT INTO `course` VALUES (27, '345345', 1001, 1, '45645', 20, 1, 2, 15);
INSERT INTO `course` VALUES (29, '12312', 1001, 1, '12312', 20, 1, 2, 15);
INSERT INTO `course` VALUES (31, 'xasdasdas', 1001, 1, '123', 12, 1, 2, 12);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleID` int(11) NOT NULL,
  `roleName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permissions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`roleID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (0, 'admin', NULL);
INSERT INTO `role` VALUES (1, 'teacher', NULL);
INSERT INTO `role` VALUES (2, 'student', NULL);

-- ----------------------------
-- Table structure for selectedcourse
-- ----------------------------
DROP TABLE IF EXISTS `selectedcourse`;
CREATE TABLE `selectedcourse`  (
  `courseID` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `mark` int(11) NULL DEFAULT NULL COMMENT '成绩',
  INDEX `selected_ibfk`(`courseID`) USING BTREE,
  CONSTRAINT `selected_ibfk` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of selectedcourse
-- ----------------------------
INSERT INTO `selectedcourse` VALUES (2, 10001, 1);
INSERT INTO `selectedcourse` VALUES (1, 10001, 100);
INSERT INTO `selectedcourse` VALUES (1, 10002, 34);
INSERT INTO `selectedcourse` VALUES (1, 10003, 78);
INSERT INTO `selectedcourse` VALUES (2, 10003, 23);
INSERT INTO `selectedcourse` VALUES (4, 10001, NULL);
INSERT INTO `selectedcourse` VALUES (34, 10001, NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `studentNumber` int(10) NOT NULL COMMENT '学生学号',
  `sex` int(1) NULL DEFAULT NULL COMMENT '1为男,0为女',
  `collegeID` int(11) NOT NULL COMMENT '院系id',
  `grade` int(2) NULL DEFAULT NULL COMMENT '所在年级',
  `birthYear` date NULL DEFAULT NULL COMMENT '出生日期',
  PRIMARY KEY (`userID`) USING BTREE,
  INDEX `college_ibfk`(`collegeID`) USING BTREE,
  CONSTRAINT `college_ibfk` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10022 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (10001, '潘泰华', 2020211105, 1, 1, 4, '1996-08-15');
INSERT INTO `student` VALUES (10002, '任乐心', 2020211203, 1, 3, 1, '1995-09-14');
INSERT INTO `student` VALUES (10003, '龙季萌', 2019211321, 1, 4, 2, '1996-09-02');
INSERT INTO `student` VALUES (10004, '谭开诚', 2018211235, 1, 2, 3, '1996-09-02');
INSERT INTO `student` VALUES (10005, '许成益', 2017211294, 1, 5, 4, '1996-09-02');
INSERT INTO `student` VALUES (10006, '潘景曜', 2017211132, 1, 1, 4, '1996-10-25');
INSERT INTO `student` VALUES (10007, '萧子平', 2019211100, 1, 3, 2, '1998-07-22');
INSERT INTO `student` VALUES (10008, '史英卓', 2019211200, 1, 2, 2, '1999-06-17');
INSERT INTO `student` VALUES (10009, '万正文', 2018211130, 1, 2, 3, '1997-07-17');
INSERT INTO `student` VALUES (10010, '罗明喆', 2018211361, 1, 1, 3, '1997-11-13');
INSERT INTO `student` VALUES (10011, '郝沐葵', 2019211152, 0, 4, 2, '1998-07-29');
INSERT INTO `student` VALUES (10012, '程珠雨', 2020211163, 0, 3, 1, '1999-06-08');
INSERT INTO `student` VALUES (10013, '龚彤云', 2017211204, 0, 1, 4, '1997-01-17');
INSERT INTO `student` VALUES (10014, '彭津文', 2018211661, 0, 5, 3, '1998-07-14');
INSERT INTO `student` VALUES (10015, '韩颜英', 2019211137, 0, 3, 2, '1999-07-21');
INSERT INTO `student` VALUES (10016, '范秀芳', 2017211164, 0, 4, 4, '1998-11-29');
INSERT INTO `student` VALUES (10017, '郭淑贤', 2018211196, 0, 5, 3, '1996-09-27');
INSERT INTO `student` VALUES (10018, '熊春娇', 2019211313, 0, 4, 2, '1999-07-17');
INSERT INTO `student` VALUES (10019, '赖贝莉', 2017211212, 0, 5, 4, '1996-11-01');
INSERT INTO `student` VALUES (10020, '赵含海', 2020211416, 0, 2, 1, '1999-03-10');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` int(1) NULL DEFAULT NULL COMMENT '男为1,女为0',
  `birthYear` date NOT NULL,
  `degree` int(2) NULL DEFAULT NULL COMMENT '学历(学士-1,硕士-2,博士-3)',
  `title` int(2) NULL DEFAULT NULL COMMENT '职称(助教-1,普通教师-2,副教授-3,教授-4)',
  `collegeID` int(11) NOT NULL COMMENT '院系',
  PRIMARY KEY (`userID`) USING BTREE,
  INDEX `teacher_ibfk`(`collegeID`) USING BTREE,
  CONSTRAINT `teacher_ibfk` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1011 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1001, '刘老师', 1, '1990-03-08', 2, 3, 2);
INSERT INTO `teacher` VALUES (1002, '张老师', 1, '1993-06-18', 1, 2, 5);
INSERT INTO `teacher` VALUES (1003, '王老师', 1, '1996-09-02', 2, 1, 4);
INSERT INTO `teacher` VALUES (1004, '李老师', 1, '1989-06-21', 2, 4, 3);
INSERT INTO `teacher` VALUES (1005, '赵老师', 1, '1980-06-19', 3, 4, 5);
INSERT INTO `teacher` VALUES (1006, '程老师', 0, '1992-06-01', 2, 3, 1);
INSERT INTO `teacher` VALUES (1007, '郭老师', 0, '1981-10-30', 3, 4, 4);
INSERT INTO `teacher` VALUES (1008, '韩老师', 0, '1997-07-24', 2, 2, 2);
INSERT INTO `teacher` VALUES (1009, '范老师', 0, '1997-06-28', 1, 1, 3);
INSERT INTO `teacher` VALUES (1010, '龚老师', 0, '1987-06-20', 2, 3, 5);

-- ----------------------------
-- Table structure for userlogin
-- ----------------------------
DROP TABLE IF EXISTS `userlogin`;
CREATE TABLE `userlogin`  (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码为123456',
  `role` int(11) NOT NULL DEFAULT 2 COMMENT '角色权限',
  PRIMARY KEY (`userID`) USING BTREE,
  INDEX `role`(`role`) USING BTREE,
  CONSTRAINT `userlogin_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`roleID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userlogin
-- ----------------------------
INSERT INTO `userlogin` VALUES (1, 'admin', '2abec21dc41c75c88cb87e7306c5e75f', 0);
INSERT INTO `userlogin` VALUES (8, '10001', '8a73921a197b52a077f0d94dfbc211d9', 2);
INSERT INTO `userlogin` VALUES (9, '10002', 'c83811ed015df101439a55813e1912d5', 2);
INSERT INTO `userlogin` VALUES (10, '10003', '01b1acb3342d98d71f5a973d36826ece', 2);
INSERT INTO `userlogin` VALUES (11, '10005', '828fbec160e7a6668b87b791ad7d2c1c', 2);
INSERT INTO `userlogin` VALUES (12, '10004', 'f3adecce35263e3550275fb400333e08', 2);
INSERT INTO `userlogin` VALUES (13, '10006', '6c8ea7b8005dbf6669ccea10098b460a', 2);
INSERT INTO `userlogin` VALUES (14, '1001', 'cf71c83531eeadaf2f20004d90dd90ec', 1);
INSERT INTO `userlogin` VALUES (15, '1002', 'c07224b39370bbfd51d9c3e6dbf0e109', 1);
INSERT INTO `userlogin` VALUES (16, '1003', 'ef1e023b34e32f18cb49e25fe8d2790e', 1);
INSERT INTO `userlogin` VALUES (17, '1006', '4a493c9f3350083d82982ab391f8d3cf', 1);

SET FOREIGN_KEY_CHECKS = 1;
