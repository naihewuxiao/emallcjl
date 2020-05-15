package com.cjl.emall.payment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cjl.emall")
@MapperScan(basePackages = "com.cjl.emall.payment.mapper")
public class EmallPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmallPaymentApplication.class, args);
    }

}
