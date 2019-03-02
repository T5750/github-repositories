# xxpay

## Runtime Environment
- [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Spring Framework 4.3.10.RELEASE](http://projects.spring.io/spring-framework)
- [Spring Boot 1.5.6.RELEASE](https://projects.spring.io/spring-boot)
- [MySQL 5.6](http://www.mysql.com/)
- [Spring Cloud Dalston.SR2](http://projects.spring.io/spring-cloud/)
- [Dubbo 2.5.3](https://github.com/apache/incubator-dubbo)

## Simple Tutorial
- `XxPay聚合支付` XxPay聚合支付使用Java开发，包括spring-cloud、dubbo、spring-boot三个架构版本，已接入微信、支付宝等主流支付渠道，可直接用于生产环境。
- 目前已经接入支付渠道：微信(公众号支付、扫码支付、APP支付、H5支付)、支付宝(电脑网站支付、手机网站支付、APP支付、当面付)；

### 项目结构
```
xxpay
├── xxpay4dubbo -- spring-boot-dubbo架构实现
|    ├── xxpay4dubbo-api -- 接口定义
|    ├── xxpay4dubbo-service -- 服务生产者
|    └── xxpay4dubbo-web -- 服务消费者
├── xxpay4spring-cloud -- spring-cloud架构实现
|    ├── xxpay-config -- 配置中心
|    ├── xxpay-gateway -- API网关
|    ├── xxpay-server -- 服务注册中心
|    ├── xxpay-service -- 服务生产者
|    └── xxpay-web -- 服务消费者
├── xxpay4spring-boot -- spring-mvc架构实现
├── xxpay-common -- 公共模块
├── xxpay-dal -- 数据持久层
├── xxpay-mgr -- 运营管理平台
└── xxpay-shop -- 演示商城
```

#### xxpay
| 项目  | 端口 | 描述
|---|---|---
|xxpay-common |  | 公共模块(常量、工具类等)，jar发布
|xxpay-dal |  | 支付数据持久层，jar发布
|xxpay-mgr | 8092 | 支付运营平台
|xxpay-shop | 8081 | 支付商城演示系统
|xxpay4spring-cloud |  | 支付中心spring-cloud架构实现
|xxpay4dubbo |  | 支付中心spring-boot-dubbo架构实现
|xxpay4spring-boot |  | 支付中心spring-mvc架构实现
#### xxpay4spring-cloud
| 项目  | 端口 | 描述
|---|---|---
|xxpay-config | 2020 | 支付服务配置中心
|xxpay-gateway | 3020 | 支付服务API网关
|xxpay-server | 2000 | 支付服务注册中心
|xxpay-service | 3000 | 支付服务生产者
|xxpay-web | 3010 | 支付服务消费者

项目启动顺序：
```
xxpay-server > xxpay-config > xxpay-service > xxpay-web > xxpay-gateway
```
#### xxpay4dubbo
| 项目  | 端口 | 描述
|---|---|---
|xxpay4dubbo-api |  | API接口定义
|xxpay4dubbo-service | 20880 | 支付服务生产者
|xxpay4dubbo-web | 3020 | 支付服务消费者

项目启动顺序：
```
xxpay4dubbo-service > xxpay4dubbo-web
```

## Results
shop项目统一扫码测试地址：[http://localhost:8081/goods/openQrPay.html](http://localhost:8081/goods/openQrPay.html)
![xxpay-min-min](https://www.wailian.work/images/2019/02/28/xxpay-min-min.png)

mgr项目地址：[http://localhost:8092](http://localhost:8092)
![mgr项目](http://docs.xxpay.org/uploads/201708/attach_14dc9a0765d9aeaa.png)

## Tips
- Commits on Apr 15, 2018
- Modified:
	- `pom.xml`
	- `application.properties`

## References
- [xxpay-master](https://github.com/jmdhappy/xxpay-master)
- [XxPay官网](http://www.xxpay.org)
- [XxPay文档库](http://docs.xxpay.org)
