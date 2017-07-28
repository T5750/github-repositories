## mybatis-generator-gui

mybatis-generator-gui是基于[mybatis generator](http://www.mybatis.org/generator/index.html)开发一款界面工具, 本工具可以使你非常容易及快速生成Mybatis的Java POJO文件及数据库Mapping文件。

![MainUI](https://cloud.githubusercontent.com/assets/3505708/22870467/834ed1c2-f1e2-11e6-9ae5-650b88722a7c.png)

### 注意事项
* 0.8.3版本请删除版本之前版本config目录下的sqlite3.db文件，重新`mvn install`安装

### 核心特性
* 按照界面步骤轻松生成代码，省去XML繁琐的学习与配置过程
* 保存数据库连接与Generator配置，每次代码生成轻松搞定
* 内置常用插件，比如offset
* 可选的去除掉对版本管理不友好的注释，这样新增或删除字段重新生成的文件比较过来清楚
* 目前已经支持Mysql、Oracle、PostgreSQL与SQL Server，暂不对其他非主流数据库提供支持。

### 要求
本工具由于使用了Java 8的众多特性，所以要求JDK <strong>1.8.0.60</strong>以上版本，对于JDK版本还没有升级的童鞋表示歉意。

### 下载
你可以从本链接下载本工具: https://github.com/astarring/mybatis-generator-gui/releases

### 启动本软件

* 方法一: 自助构建

```bash
    git clone https://github.com/astarring/mybatis-generator-gui
    cd mybatis-generator-gui
    mvn jfx:jar
    cd target/jfx/app/
    java -jar mybatis-generator-gui.jar
```

* 方法二: IDE中运行

Eclipse or IntelliJ IDEA中启动, 找到```com.zzg.mybatis.generator.MainUI```类并运行就可以了


### 文档
更多详细文档请参考本库的Wiki
* [Usage](https://github.com/astarring/mybatis-generator-gui/wiki/Usage-Guide)


### Tips

- Commits on Jun 17, 2017
- [mybatis-generator-gui/src/main/lib/](https://github.com/astarring/mybatis-generator-gui/tree/master/src/main/lib)

### 原文传送门
[mybatis-generator-gui](https://github.com/astarring/mybatis-generator-gui)
