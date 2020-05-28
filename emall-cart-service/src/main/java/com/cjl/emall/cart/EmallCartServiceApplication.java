package com.cjl.emall.cart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.cjl.emall.cart.mapper")
@ComponentScan("com.cjl.emall")
public class EmallCartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmallCartServiceApplication.class, args);
    }

}
