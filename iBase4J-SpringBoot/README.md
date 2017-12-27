# iBase4J-SpringBoot

iBase4J的SpringBoot版本

[![License](http://img.shields.io/:license-apache-blue.svg "2.0")](http://www.apache.org/licenses/LICENSE-2.0.html)
[![JDK 1.8](https://img.shields.io/badge/JDK-1.8-green.svg "JDK 1.8")]()

## iBase4J项目简介

- iBase4J是Java语言的分布式系统架构。 使用Spring整合开源框架。
- 使用Maven对项目进行模块化管理，提高项目的易开发性、扩展性。
- 系统包括4个子系统：系统管理Service、系统管理Web、业务Service、业务Web。
- 系统管理：包括用户管理、权限管理、数据字典、系统参数管理等等。
- 业务相关：您的业务开发。
- 可以无限的扩展子系统，子系统之间使用Dubbo或MQ进行通信。

## 主要功能
 1. 数据库：Druid数据库连接池，监控数据库访问性能，统计SQL的执行性能。 数据库密码加密，加密方式请查看PropertiesUtil，decryptProperties属性配置需要解密的key。
 2. 持久层：mybatis持久化，使用MyBatis-Plus优化，减少sql开发量；aop切换数据库实现读写分离。Transtraction注解事务。
 3. MVC： 基于spring mvc注解,Rest风格Controller。Exception统一管理。
 4. 调度：Spring+quartz, 可以查询、修改周期、暂停、删除、新增、立即执行，查询执行记录等。
 5. 基于session的国际化提示信息，职责链模式的本地语言拦截器,Shiro登录、URL权限管理。会话管理，强制结束会话。
 6. 缓存和Session：注解redis缓存数据，Spring-session和redis实现分布式session同步，重启服务会话不丢失。
 7. 多系统交互：Dubbo,ActiveMQ多系统交互，ftp/sftp/fastdafs发送文件到独立服务器，使文件服务分离。
 8. 前后端分离：没有权限的文件只用nginx代理即可。
 9. 日志：log4j2打印日志，业务日志和调试日志分开打印。同时基于时间和文件大小分割日志文件。
 10. QQ、微信、新浪微博第三方登录。
 11. 工具类：excel导入导出，汉字转拼音，身份证号码验证，数字转大写人民币，FTP/SFTP/fastDFS上传下载，发送邮件，redis缓存，加密等等。

## 技术选型
    ● 核心框架：Sring boot + Spring Framework 5.0.0 + Dubbo 2.5.3 + ibase4j-common 1.1.3
    ● 安全框架：Apache Shiro 1.2
    ● 任务调度：Spring + Quartz
    ● 持久层框架：MyBatis 3.4 + MyBatis-Plus 2.1
    ● 数据库连接池：Alibaba Druid 1.0
    ● 缓存框架：Redis
    ● 会话管理：Spring-Session 1.3.0
    ● 日志管理：SLF4J、Log4j2
    ● 前端框架：Angular JS + Bootstrap + Jquery

## 启动说明
    * 项目依赖activemq、Redis和ZooKeeper服务。
    * 使用nginx代理UI：修改配置里的UI目录后重启nginx。
    * 启动方法：
    	 	SysServiceApplication.java
    	 	SysWebApplication.java
    * 测试环境打包命令：
    	 clean package -P test -f pom-sys-service-server.xml
    	 clean package -P test -f pom-sys-web-server.xml
    * 生产环境打包命令：
    	 clean package -P product -f pom-sys-service-server.xml
    	 clean package -P product -f pom-sys-web-server.xml

## UI效果图

![登录](http://git.oschina.net/iBase4J/iBase4J/raw/master/img/login.png "登录")
![主页](http://git.oschina.net/iBase4J/iBase4J/raw/master/img/index.png "主页")
![接口](http://git.oschina.net/iBase4J/iBase4J/raw/master/img/swagger.png "接口")

## Tips

- Commits on Dec 21, 2017

## Links
- [iBase4J-SpringBoot](https://github.com/iBase4J/iBase4J-SpringBoot)
- [iBase4J github](https://github.com/iBase4J/iBase4J)
- [iBase4J gitee](https://gitee.com/iBase4J/iBase4J)
