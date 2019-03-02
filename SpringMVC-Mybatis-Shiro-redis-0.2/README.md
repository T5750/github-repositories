## 基于SpringMVC、Mybatis、Redis、Freemarker的Shiro管理Demo源码的升级版

### Runtime Environment
- IoC容器: Spring 4.2.5.RELEASE
- web框架: Spring MVC 4.2.5.RELEASE
- orm框架: MyBatis 3.1.0
- 安全框架: Shiro 1.2.5
- 数据源: DBCP 1.4
- 日志: SLF4J 1.7.16
- 前端框架: Bootstrap 3.3.5
- 模板引擎: FreeMarker 2.3.20
- Redis 2.8.0

### Quick Process
1. 将源代码导入IDEA中
2. 执行SpringMVC-Mybatis-Shiro-redis-0.2\sql\springmvc-mybatis-shiro-redis-0.2.sql
3. 启动redis-server
4. 部署项目，启动tomcat服务器
5. 运行效果

![SpringMVC-Mybatis-Shiro-redis-0.2-min-min](https://www.wailian.work/images/2019/02/28/SpringMVC-Mybatis-Shiro-redis-0.2-min-min.png)

### Accounts
管理员帐号：admin
密码：sojson
ps:定时任务的sql会把密码改变为sojson.com

### Tips
在原项目的基础上
> 1. 由JDK 1.7 改为 JDK 1.6，将shiro-freemarker-tags-0.1.jar反编译至com\jagregory\shiro\freemarker中

### References
- [SpringMVC-Mybatis-Shiro-redis-0.2](https://github.com/baichengzhou/SpringMVC-Mybatis-Shiro-redis-0.2)