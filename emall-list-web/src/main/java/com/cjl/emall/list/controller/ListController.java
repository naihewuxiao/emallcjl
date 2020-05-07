package com.cjl.emall.list.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;

import com.cjl.emall.bean.SkuLsParams;
import com.cjl.emall.bean.SkuLsResult;
import com.cjl.emall.service.ListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ListController {

    @Reference
    private ListService listService;

    // http://localhost:8086/list.html?keyword=小米&catalog3Id=61&valueId=81&pageNo=1&pageSize=10
    @RequestMapping("list.html")
    @ResponseBody
    public String getList(SkuLsParams skuLsParams){
        SkuLsResult skuLsResult = listService.search(skuLsParams);

        // 将其变成字符串
        String string = JSON.toJSONString(skuLsResult);

        return string;
    }

}
