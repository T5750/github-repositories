# Guns

## Runtime Environment
- [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Spring Framework 4.3.13.RELEASE](http://projects.spring.io/spring-framework)
- [Spring Boot 1.5.9.RELEASE](https://projects.spring.io/spring-boot)
- [MyBatis-Plus 2.1.8](https://github.com/baomidou/mybatis-plus)
- [Shiro 1.4.0](http://shiro.apache.org/download.html)
- [MySQL 5.6](http://www.mysql.com/)
- [Druid 1.1.6](https://github.com/alibaba/druid)
- [Ehcache 3.3.1](http://www.ehcache.org/downloads/)
- [Swagger2](https://swagger.io/)
- [Flowable 6.2.0](https://www.flowable.org/downloads.html)

## Simple Tutorial
### 介绍
Guns基于SpringBoot,致力于做更简洁的后台管理系统,完美整合springmvc + shiro + mybatis-plus + beetl + flowable!Guns项目代码简洁,注释丰富,上手容易,同时Guns包含许多基础模块(用户管理,角色管理,部门管理,字典管理等10个模块),可以直接作为一个后台管理系统的脚手架! 2018目标 `更简洁`,`更规范`!

Guns v3.0新增rest api服务,提供对接服务端接口的支持,并利用jwt token鉴权机制给予客户端的访问权限,传输数据进行md5签名保证传输过程数据的安全性!

Guns v3.1新增集成工作流引擎flowable6.2.0!

### 管理系统功能
1.用户管理 2.角色管理 3.部门管理 4.菜单管理 5.字典管理 6.业务日志 7.登录日志 8.监控管理 9.通知管理 10.代码生成

### guns-admin
- [http://localhost:8080/](http://localhost:8080/)
- 帐号：admin，密码：111111

### guns-rest
#### Step 1 
[http://localhost/auth?userName=admin&password=admin](http://localhost/auth?userName=admin&password=admin)
```
{"randomKey": "5wnxh0","token": "eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiI1d254aDAiLCJzdWIiOiJhZG1pbiIsImV4cCI6MTUyODE3OTkzNCwiaWF0IjoxNTI3NTc1MTM0fQ.lynv3oxpfIop2P4wsUj2E20BwAmjfHBpX7SaJ0aMU-qprPVL-5zE76U0GT0RG-_7HocOU43LNLNUgjO36u5jxw"}
```

#### Step 2
`DecryptTest`
```
{"object":"eyJhZ2UiOjEyLCJuYW1lIjoiZmZmZiIsInRpcHMiOiJjb2RlIiwidXNlciI6InN0eWxlZmVuZyJ9","sign":"9f99c7c07fcc27053e62a61c5e7fa7fc"}
```

#### Step 3
[http://localhost/hello](http://localhost/hello)
- Postman
- Authorization -> Bearer Token: ```eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiI1d254aDAiLCJzdWIiOiJhZG1pbiIsImV4cCI6MTUyODE3OTkzNCwiaWF0IjoxNTI3NTc1MTM0fQ.lynv3oxpfIop2P4wsUj2E20BwAmjfHBpX7SaJ0aMU-qprPVL-5zE76U0GT0RG-_7HocOU43LNLNUgjO36u5jxw```
- Body -> raw -> JSON(application/json)

#### Step 4
- Console: 签名校验成功!stylefeng
- Postman: "请求成功!"

## Results
![swagger](https://git.oschina.net/uploads/images/2017/0604/194616_36ed7fd6_551203.png)
![security](https://git.oschina.net/uploads/images/2017/0526/103746_6b4129ed_551203.png)

## Tips
- Commits on Mar 15, 2018
- Modified:
	- `pom.xml`
	- `application.yml`
	- `logback-spring.xml`

## Links
- [Guns](https://github.com/stylefeng/Guns)
