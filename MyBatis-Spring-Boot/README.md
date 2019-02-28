## MyBatis-Spring-Boot

### Runtime Environment
- [Spring Boot 1.5.1.RELEASE](https://github.com/spring-projects/spring-boot)
- [mybatis-spring-boot-starter](https://github.com/mybatis/spring-boot-starter)
- [mapper-spring-boot-starter](https://github.com/abel533/mapper-boot-starter)
- [pagehelper-spring-boot-starter](https://github.com/pagehelper/pagehelper-spring-boot)

### Dependencies
```
<!--mybatis-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.1.1</version>
</dependency>
<!--mapper-->
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>1.1.1</version>
</dependency>
<!--pagehelper-->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.1.1</version>
</dependency>
```

### application.properties
```
#mybatis
mybatis.type-aliases-package=tk.mybatis.springboot.model
mybatis.mapper-locations=classpath:mapper/*.xml

#mapper
#mappers 多个接口时逗号隔开
mapper.mappers=tk.mybatis.springboot.util.MyMapper
mapper.not-empty=false
mapper.identity=MYSQL

#pagehelper
pagehelper.helperDialect=mysql
pagehelper.reasonable=true
pagehelper.supportMethodsArguments=true
pagehelper.params=count=countSql
```

### Tips
- Commits on Jun 10, 2017

![MyBatis-Spring-Boot-min](https://www.wailian.work/images/2019/02/28/MyBatis-Spring-Boot-min.png)

### Links
[MyBatis-Spring-Boot](https://github.com/abel533/MyBatis-Spring-Boot)
[Mapper](https://github.com/abel533/Mapper)
[Mybatis-PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)