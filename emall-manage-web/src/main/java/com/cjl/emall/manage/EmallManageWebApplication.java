package com.cjl.emall.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cjl.emall")
public class EmallManageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmallManageWebApplication.class, args);
    }

}
