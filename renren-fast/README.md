# renren-fast

**项目说明**
- renren-fast是一个轻量级的Java快速开发平台，能快速开发项目并交付【接私活利器】
<br> 

**具有如下特点** 
- 友好的代码结构及注释，便于阅读及二次开发
- 实现前后端分离，通过token进行数据交互，前端再也不用关注后端技术
- 灵活的权限控制，可控制到页面或按钮，满足绝大部分的权限需求
- 页面交互使用Vue2.x，极大的提高了开发效率
- 完善的代码生成机制，可在线生成entity、xml、dao、service、html、js、sql代码，减少70%以上的开发任务
- 引入quartz定时任务，可动态完成任务的添加、修改、删除、暂停、恢复及日志查看等功能
- 引入API模板，根据token作为登录令牌，极大的方便了APP接口开发
- 引入Hibernate Validator校验框架，轻松实现后端校验
- 引入云存储服务，已支持：七牛云、阿里云、腾讯云等
- 引入swagger文档支持，方便编写API接口文档
- 引入路由机制，刷新页面会停留在当前页
<br> 

**项目结构** 
```
renren-fast
├─doc  项目SQL语句
│
├─common 公共模块
│  ├─aspect 系统日志
│  ├─exception 异常处理
│  ├─validator 后台校验
│  └─xss XSS过滤
│ 
├─config 配置信息
│ 
├─modules 功能模块
│  ├─api API接口模块(APP调用)
│  ├─job 定时任务模块
│  ├─oss 文件服务模块
│  └─sys 权限模块
│ 
├─RenrenApplication 项目启动类
│  
├──resources 
│  ├─mapper SQL对应的XML文件
│  ├─static 第三方库、插件等静态资源
│  └─views  项目静态页面

```
<br> 

**如何交流、反馈、参与贡献？** 
- 开发文档：http://www.renren.io/open/renren-fast/
- Git仓库：http://git.oschina.net/babaio/renren-fast
- 其他项目：http://www.renren.io/open/
- [编程入门教程](http://www.renren.io)：http://www.renren.io   
- 官方QQ群：324780204、145799952
- 如需关注项目最新动态，请Watch、Star项目，同时也是对项目最好的支持
<br> 

**技术选型：** 
- 核心框架：Spring Boot 1.5
- 安全框架：Apache Shiro 1.3
- 视图框架：Spring MVC 4.3
- 持久层框架：MyBatis 3.3
- 定时器：Quartz 2.3
- 数据库连接池：Druid 1.0
- 日志管理：SLF4J 1.7、Log4j
- 页面交互：Vue2.x 
<br> 

 **本地部署**
- 通过git下载源码
- 创建数据库renren_fast，数据库编码为UTF-8
- 执行doc/db.sql文件，初始化数据
- 修改application-dev.yml，更新MySQL账号和密码
- Eclipse、IDEA运行RenrenApplication.java，则可启动项目
- 项目访问路径：http://localhost:8080/renren-fast
- 账号密码：admin/admin
- Swagger路径：http://localhost:8080/renren-fast/swagger/index.html

 **项目演示**
- 演示地址：http://demo.open.renren.io/renren-fast
- 账号密码：admin/admin
<br> 

**接口文档效果图：**
![输入图片说明](http://cdn.renren.io/img/6e8d7575fb8240d49b949dc0f02547bc "在这里输入图片标题")
<br> <br> <br> 

**演示效果图：**
![输入图片说明](http://cdn.renren.io/img/4f15a5513e4e4a00a07294e87c548982 "在这里输入图片标题")
![renren-fast-menus-min](https://www.wailian.work/images/2019/02/28/renren-fast-menus-min.png)
![输入图片说明](http://cdn.renren.io/img/f59b6f61c36f49e1851a5bf3e91a1e5b "在这里输入图片标题")
![输入图片说明](http://cdn.renren.io/img/c3fe6c2146dc450f95b5b85d0ad0325f "在这里输入图片标题")
![输入图片说明](http://cdn.renren.io/img/069045e6c6d24d88b6c2827a1b625da4 "在这里输入图片标题")
![输入图片说明](http://cdn.renren.io/img/5d4d6c1acd5c4455930dc5dc7d88ad82 "在这里输入图片标题")
![输入图片说明](http://cdn.renren.io/img/35dfe497ea7642028c7d6115a5a1c5e8 "在这里输入图片标题")

## Tips
- Commits on 2017-12-25
- app
    - register: ```http://localhost:8080/renren-fast/app/register?mobile=18888888888&password=123456```
    - login: ```http://localhost:8080/renren-fast/app/login?mobile=18888888888&password=123456```
    - userInfo: ```http://localhost:8080/renren-fast/app/userInfo```
    - userId: ```http://localhost:8080/renren-fast/app/userId```
    - notToken: ```http://localhost:8080/renren-fast/app/notToken```

## Links
- [renren-fast](https://gitee.com/babaio/renren-fast)
