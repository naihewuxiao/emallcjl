package com.cjl.emall.usermanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.cjl.emall.usermanage.mapper")
public class EmallUsermanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmallUsermanageApplication.class, args);
    }

}
