package com.cjl.emall.usermanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.cjl.emall.usermanage.mapper")
@ComponentScan(basePackages = "com.cjl.emall")
public class EmallUsermanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmallUsermanageApplication.class, args);
    }

}
