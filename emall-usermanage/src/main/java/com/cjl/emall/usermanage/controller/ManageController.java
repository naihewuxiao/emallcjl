package com.cjl.emall.usermanage.controller;

import com.cjl.emall.bean.UserInfo;
import com.cjl.emall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user/management")
public class ManageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<UserInfo> findAll(){
        return userService.findAll();
    }
}
