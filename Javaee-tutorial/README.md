## 简介
这是个简单的教务系统网站,并且结合了图书订购功能,希望这个小DEMO能对大家学习有帮助

## 使用技术
- IoC容器: Spring 4.2.4.RELEASE
- web框架: Spring MVC 4.2.4.RELEASE
- orm框架: MyBatis 3.3.1
- 安全框架: Shiro 1.2.4
- 数据源: DBCP 1.4
- 日志: SLF4J 1.7.14
- Json: Gson 2.5
- 前端框架: Bootstrap v3.3.6

## 起步
1. 下载Mysql,创建一个数据库名字为javaee-tutorial,导入javaee-tutorial.sql,直接运行其中sql即可
2. 下载Tomcat
3. 使用Intellij Idea导入项目,之后配置项目启动方式,使用刚才下载好的Tomcat
4. 运行

![image](http://img.my.csdn.net/uploads/201704/14/1492181839_6548.png)

## 使用简介
权限介绍,运行网站初始时会存在四个权限角色:
1. 管理员,账号为admin
2. 学生,账号为student
3. 教师,账号为teache
4. 供应商,账号为supplier

*密码均为123456*

## 功能介绍
###  1. 基本信息功能
其中可以设置一些基本的学校信息,也就是数据库中的实体-关系,之后就可以基于这些基本的关系信息进行更加复杂的功能,例如选课后可以记录多少学生选择了这门课程,只会教师提交图书后计算图书总数可以通过其中的关系得到
1. 系部信息
2. 专业信息
3. 班级信息
4. 课程信息
5. 学生管理

![image](http://img.my.csdn.net/uploads/201704/14/1492181356_1380.png)

### 2. 图书管理功能
1. 教师上传图书
2. 秘书审批图书
3. 查看已审核图书

![image](http://img.my.csdn.net/uploads/201704/14/1492181328_8251.png)

## 数据库表结构
![image](http://7xi78h.com1.z0.glb.clouddn.com/db.png)

## 原文传送门
[Javaee-tutorial](https://github.com/C0de8ug/Javaee-tutorial)

## 变更
在原项目的基础上
> 1. 由Java 8 改为 Java 6，LocalDate 改为 Date，DBCP2 2.1 改为 DBCP 1.4
> 2. Tomcat 7.0.77
> 3. 移除未引用的js和css等