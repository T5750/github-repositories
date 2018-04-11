package com.xai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ServletComponentScan
@MapperScan({"com.xai.*.dao","com.xai.*.*.dao"})
@SpringBootApplication
public class BootdoApplication extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootdoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BootdoApplication.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ    XIAOSHUAIAI启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" +
                " __   _______          ____   _____ _    _ _    _         _____          _____ \n" +
                " \\ \\ / /_   _|   /\\   / __ \\ / ____| |  | | |  | |  /\\   |_   _|   /\\   |_   _|\n" +
                "  \\ V /  | |    /  \\ | |  | | (___ | |__| | |  | | /  \\    | |    /  \\    | |  \n" +
                "   > <   | |   / /\\ \\| |  | |\\___ \\|  __  | |  | |/ /\\ \\   | |   / /\\ \\   | |  \n" +
                "  / . \\ _| |_ / ____ \\ |__| |____) | |  | | |__| / ____ \\ _| |_ / ____ \\ _| |_ \n" +
                " /_/ \\_\\_____/_/    \\_\\____/|_____/|_|  |_|\\____/_/    \\_\\_____/_/    \\_\\_____|");

    }




}
