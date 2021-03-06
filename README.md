# 简介
本项目是基于Springboot+Mybatis+ThymeLeaf实现的教务查询系统

# 技术栈
Web框架：Springboot

ORM框架：Mybatis

安全框架：Shiro

模板引擎：ThymeLeaf

前端框架：Bootstrap

# 快速开始
## 运行环境和工具
* 编译器：IntelliJ IDEA 2020.2
* 项目构建工具：Maven 3.6.3
* 数据库：Mysql5.7
* JDK版本：jdk1.8

## 初始化
* 在Mysql中，创建一个数据库名称为jwxt的数据库，并导入SQL目录下的.sql 文件
* 使用 IntelliJ IDEA 导入Maven项目
* 下载css文件放到src/main/resources/static/css目录下（链接：https://pan.baidu.com/s/18VWV8xxKDqVnACQh2dJBeQ 提取码：aw1c）
* 在application.properties中配置数据库
* 运行

# 功能模块介绍
## 登录模块
* 使用Shiro权限管理框架，实现登录验证和登录信息的储存
* 根据不同权限的登录账户分发角色，对不同页面url进行权限管理

## 管理员模块功能
* 管理员可对教师信息、学生信息、课程信息进行增删改查操作
* 当课程已经有学生选课成功时，将无法删除
* 管理员可以添加用于登录的用户信息，并且可以可以重置非管理员账户的密码
* 对于修改用户密码操作限制了新密码不可以和原密码相同，密码以MD5盐值加密方式存储

## 教师模块功能
* 教师可以获取其教授的课程列表，并且可以修改课程的部分信息
* 教师可以给选择该课程的同学打分，但无法对已经打分的同学进行二次操作
* 教师可以修改自己的密码

## 学生模块功能
* 学生可以获取已经修读完的课程及其分数
* 学生可以查询院系中自己能够选择的课程
* 在教师没有给予成绩之前可以进行自由选课和退课
* 学生可以修改自己的密码

# 版本控制
该项目使用Git进行版本管理

# 作者
_WanderZhang_
