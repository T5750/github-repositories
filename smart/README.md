# Smart

## 简述
    Smart定位于用当下最流行的技术，为您构建一个易理解、高可用、高扩展性的应用基层，实现快速开发。内置Dubbo服务治理、单点登录权限系统(按钮级，权限修改实时生效)、支持分布式的定时任务服务及代码生成器、易用高兼容的boostrap前端Html模板。

## 组织结构

``` lua
smart
├── smart-mvc -- 公共模块（SpringMVC + Spring + Mybatis/Hibernate）
|    ├── smart-mvc-core -- 公共模块核心
|    ├── smart-mvc-hibernate -- 公共模块hibernate特有
|    ├── smart-mvc-mybatis -- 公共模块mybatis特有
├── smart-sso -- 单点登录权限系统
|    ├── smart-sso-client -- 单点登录客户端依赖包，提供单点认证、授权管理
|    ├── smart-sso-rpc -- 单点登录远程调用API
|    ├── smart-sso-server -- 单点登录服务端
├── smart-static -- 公用静态js、css文件
├── smart-tool -- 代码生成工具
|    ├── smart-tool-mybatis -- 代码生成工具（mybatis）版本
├── smart-demo -- 简单的单点登录Dubbo服务化案例
|    ├── smart-demo-api -- demo远程调用API
|    ├── smart-demo-server -- demo服务化provider
|    ├── smart-demo-web -- demo服务化consumer(单点登录权限拦截器)
```

## 技术选型

### 后端
- JDK：1.8（支持1.7+）
- 数据库：Mysql
- 项目构建工具：Maven 3.3.3
- MVC框架：SpringMVC 4.2.1.RELEASE
- 核心框架：Spring 4.2.1.RELEASE
- ORM框架：MyBatis 3.3.0/Hibernate 4.2.7.Final
- 分布式协调服务：Zookeeper 3.4.7
- 分布式RPC服务：Dubbo 2.5.3
- 分布式缓存服务：Redis 2.8.12
- 分布式消息服务：ActiveMQ 5.13.3
- NIO框架：Netty 4.0.23.Final
- JSON工具：Fastjson 1.2.29
- 定时任务：Quartz 2.2.1
- 数据库连接池：Druid 1.0.15
- 日志管理：SLF4J 1.7.21、Logback 1.1.7
- 模板引擎：Freemarker 2.3.23
- 单点登录：极简基于Cookie实现
- 代码生成工具：基于GUI实现

### 前端
- 基础代码库：Jquery 2.1.1
- 前端模板：Ace 1.3.3(Bootstrap) https://github.com/bopoda/ace

### 浏览器兼容
- Internet Explorer 11
- Internet Explorer 10
- Internet Explorer 9
- Internet Explorer 8
- Google Chrome 14+
- Firefox 5+
- Safari 5
- Opera 11
- 手机浏览器兼容

## 架构图
![架构图](http://img.blog.csdn.net/20170413094648142?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 数据库模型
![数据库模型](http://img.blog.csdn.net/20170228162027225?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 效果展示

### 代码生成器
![代码生成器](http://img.blog.csdn.net/20170228171253734?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![](http://img.blog.csdn.net/20170118203537172?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### Dubbo监控页
![](http://img.blog.csdn.net/20170119151157271?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 单点登录页
![](http://img.blog.csdn.net/20170106172009071?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 权限管理页
![](http://img.blog.csdn.net/20170106172032962?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![](http://img.blog.csdn.net/20170106172050728?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![](http://img.blog.csdn.net/20170106172102416?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 手机浏览器展示
![](http://img.blog.csdn.net/20170106172646403?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![](http://img.blog.csdn.net/20170106172905092?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![](http://img.blog.csdn.net/20170106172915803?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![](http://img.blog.csdn.net/20170106172926694?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTQ2NjM1MDY2NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 原文传送门
[Smart](https://github.com/a466350665/smart)

## 变更
在原项目的基础上
> 1. 由JDK 1.8 改为 JDK 1.6，增加java.beans.Transient.java
