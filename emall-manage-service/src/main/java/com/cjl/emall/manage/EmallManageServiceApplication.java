package com.cjl.emall.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.cjl.emall.manage.mapper")
public class EmallManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmallManageServiceApplication.class, args);
    }

}
