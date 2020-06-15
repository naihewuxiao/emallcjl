package com.cjl.emall.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cjl.emall")
public class EmallOrderWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmallOrderWebApplication.class, args);
    }

}
