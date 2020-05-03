package com.cjl.emall.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cjl.emall")
public class EmallItemWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmallItemWebApplication.class, args);
    }

}
