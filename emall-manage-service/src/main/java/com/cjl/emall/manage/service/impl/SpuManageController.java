package com.cjl.emall.manage.service.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpuManageController {
    @RequestMapping("spuListPage")
    public String getSpuListPage(){
        return "spuListPage";
    }
}


