package com.cjl.emall.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.cjl.emall.manage.mapper")
@ComponentScan("com.cjl.emall")
public class EmallManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmallManageServiceApplication.class, args);
    }

}
