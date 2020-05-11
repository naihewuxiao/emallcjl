package com.cjl.emall.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan("com.cjl.emall")
public class EmallCartWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmallCartWebApplication.class, args);
    }

}
