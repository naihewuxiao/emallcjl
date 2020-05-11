package com.cjl.emall.list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cjl.emall")
public class EmallListWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmallListWebApplication.class, args);
    }

}
