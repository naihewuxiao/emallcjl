package com.cjl.emall.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.cjl.emall.order.mapper")
@ComponentScan("com.cjl.emall")
public class EmallOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmallOrderServiceApplication.class, args);
    }

}
