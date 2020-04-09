package com.cjl.emall.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cjl.emall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private UserService userService;


}
